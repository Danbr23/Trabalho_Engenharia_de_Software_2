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
	

    private TabPane tabPane = new TabPane();
    private CommandManager commandManager = new CommandManager();
    private EditorTabFactory tabFactory;

    @Override
    public void start(Stage primaryStage) {
    	
    	
    	BorderPane root = new BorderPane();
    	
    	SearchBar searchBar = new SearchBar();
    	SearchBarUI searchBarUI = new SearchBarUI(searchBar);
    	tabFactory = new CodeEditorTabFactory(searchBar);
    	
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
    
    private MenuBar createMenuBar() {
    	MenuBar menuBar = new MenuBar();
    	Menu fileMenu = new Menu("Arquivo");
    	Menu editMenu = new Menu("Editar");
    	
    	MenuItem newTabItem = new MenuItem("Nova aba");
    	newTabItem.setOnAction(e -> addNewTab());
    	
    	MenuItem copyItem = new MenuItem("Copy");
    	copyItem.setOnAction(e -> executeCommand(new CopyCommand(getCurrentCodeArea())));
    	
    	MenuItem pasteItem = new MenuItem("Colar");
    	pasteItem.setOnAction(e -> executeCommand(new PasteCommand(getCurrentCodeArea())));
    	
    	MenuItem undoItem = new MenuItem("Desfazer");
    	undoItem.setOnAction(e -> commandManager.undo());
    	
    	MenuItem redoItem = new MenuItem("Refazer");
    	redoItem.setOnAction(e -> commandManager.redo());
    	
    	MenuItem saveItem = new MenuItem("Salvar");
    	saveItem.setOnAction(e -> saveFile());
    	
    	fileMenu.getItems().addAll(newTabItem,saveItem);
    	editMenu.getItems().addAll(copyItem,pasteItem,undoItem,redoItem);
    	
    	menuBar.getMenus().addAll(fileMenu,editMenu);
    	return menuBar;
    	
    }
    
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
    
    private void addNewTab() {
    	
    	Tab newTab = tabFactory.createTab();
    	tabPane.getTabs().add(newTab);
    	tabPane.getSelectionModel().select(newTab);
    }
    
    private CodeArea getCurrentCodeArea() {
    	Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
    	if(selectedTab != null) {
    		ScrollPane scrollPane = (ScrollPane) selectedTab.getContent();
    		return (CodeArea) scrollPane.getContent();
    	}
    	return null;
    }
    
    private void executeCommand(Command command) {
    	if(command != null) {
    		commandManager.executeCommand(command);
    	}
    }
}