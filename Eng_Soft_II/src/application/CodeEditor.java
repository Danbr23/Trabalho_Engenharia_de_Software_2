package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

import org.fxmisc.richtext.CodeArea;

import command.*;
import tabFactory.*;
import observer.*;

// Aplicação JavaFX
public class CodeEditor extends Application {
	

    private TabPane tabPane = new TabPane(); // GUI
    //Instância do CommandManager que vai ficar responsável por guardar o histórico de comandos em pilhas para desfaze-los e refaze-os. 
    private CommandManager commandManager = new CommandManager(); 
    private EditorTabFactory tabFactory; // Instância da "Fábrica" de abas que cria e destroi de forma dinâmica as abas do editor.

    @Override
    public void start(Stage primaryStage) {
    	
    	//Criação padrão de GUI do JavaFX 
    	BorderPane root = new BorderPane();
    	
    	SearchBar searchBar = new SearchBar(); //Parte lógica da barra de pesquisa que vai estar lidando com o Observer
    	SearchBarUI searchBarUI = new SearchBarUI(searchBar); // Parte gráfica da barra de pesquisa
    	tabFactory = new CodeEditorTabFactory(searchBar); // Cria a o EditorTabFactory passando a searchBar por parâmetro, o que significa que
    	// o searchBar vai adicionar a tabFactory como observador, e quando alguma mudança acontecer, ele notifica a tabFactory.
    	
    	//Criação padrão de GUI do JavaFX e criação do menu
    	MenuBar menuBar = createMenuBar();
    	root.setTop(menuBar);
    	root.setCenter(tabPane);
    	root.setBottom(searchBarUI.createSearchBar());    		
    	addNewTab();
    	Scene scene = new Scene(root, 800, 600);
    	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	primaryStage.setTitle("Editor de código C");
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    //Criação do Menu
    private MenuBar createMenuBar() {
    	MenuBar menuBar = new MenuBar();
    	Menu fileMenu = new Menu("Arquivo");
    	Menu editMenu = new Menu("Editar");
    	
    	MenuItem newTabItem = new MenuItem("Nova aba");
    	newTabItem.setOnAction(e -> addNewTab()); // Cria uma nova aba
    	
    	MenuItem copyItem = new MenuItem("Copy");
    	copyItem.setOnAction(e -> executeCommand(new CopyCommand(getCurrentCodeArea()))); //Cria um objeto do comando de Copiar sempre que a opção é selecionada
    	
    	MenuItem pasteItem = new MenuItem("Colar");
    	pasteItem.setOnAction(e -> executeCommand(new PasteCommand(getCurrentCodeArea()))); //Cria um objeto do comando de Colar sempre que a opção é selecionada
    	
    	MenuItem undoItem = new MenuItem("Desfazer");
    	undoItem.setOnAction(e -> commandManager.undo()); // Desfaz o último comando, tira ele da pilha de comandos feitos e adiciona ele na pilha de comandos que podem ser refeitos
    	
    	MenuItem redoItem = new MenuItem("Refazer"); // Executa o comando no topo da pilha de comandos desfeitos/que podem ser refeitos, tira ele dessa pilha e volta ele pra pilha de comandos executados
    	redoItem.setOnAction(e -> commandManager.redo());
    	
    	MenuItem saveItem = new MenuItem("Salvar");
    	saveItem.setOnAction(e -> saveFile()); //Opção para salvar o arquivo
    	
    	fileMenu.getItems().addAll(newTabItem,saveItem);
    	editMenu.getItems().addAll(copyItem,pasteItem,undoItem,redoItem);
    	
    	menuBar.getMenus().addAll(fileMenu,editMenu);
    	return menuBar;
    	
    }
    
    
    //Função básica para salvar arquivos
    private void saveFile() {
    	Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
    	if(selectedTab == null) return;
    	
    	ScrollPane scrollPane = (ScrollPane) selectedTab.getContent();
    	CodeArea codeArea = (CodeArea) scrollPane.getContent();
    	
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivos C (*.c)","*.c")); 
    	File file = fileChooser.showSaveDialog(null);
    	
    	if(file != null) {
    		try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
    			writer.write(codeArea.getText());
    		}catch(IOException e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    // Chama a "Fábrica" de abas para criar mais uma aba
    private void addNewTab() {
    	
    	Tab newTab = tabFactory.createTab();
    	tabPane.getTabs().add(newTab);
    	tabPane.getSelectionModel().select(newTab);
    }
    
    //Pega o objeto CodeArea que contém o que foi escrito pelo usuário
    private CodeArea getCurrentCodeArea() {
    	Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
    	if(selectedTab != null) {
    		ScrollPane scrollPane = (ScrollPane) selectedTab.getContent();
    		return (CodeArea) scrollPane.getContent();
    	}
    	return null;
    }
    
    // Executa os comandos de gerenciamento do CommandMananager, que gerencia o Do e Redo dos comandos
    private void executeCommand(Command command) {
    	if(command != null) {
    		commandManager.executeCommand(command);
    	}
    }
}