package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tabFactory.CodeEditorTabFactory;
import tabFactory.EditorTabFactory;

import java.util.Stack;

import org.fxmisc.richtext.CodeArea;

import command.Command;
import command.CommandManager;
import command.CopyCommand;
import command.PasteCommand;

// Aplicação JavaFX
public class CodeEditor extends Application {
	
    //private TextArea textArea = new TextArea();
    //private Button copyButton = new Button("Copr");
    //private Button pasteButton = new Button("Colar");
    //private Button undoButton = new Button("Desfazer");
    private TabPane tabPane = new TabPane();
    private CommandManager commandManager = new CommandManager();
    private EditorTabFactory tabFactory = new CodeEditorTabFactory();

    @Override
    public void start(Stage primaryStage) {
    	
    	/*
        copyCommand = new CopyCommand(textArea);

        copyButton.setOnAction(e -> copyCommand.execute());
        pasteButton.setOnAction(e -> {
            PasteCommand pasteCommand = new PasteCommand(textArea, copyCommand);
            commandManager.executeCommand(pasteCommand);
            undoButton.setDisable(!commandManager.hasHistory());
        });
        undoButton.setOnAction(e -> {
            commandManager.undoLastCommand();
            undoButton.setDisable(!commandManager.hasHistory());
        });

        undoButton.setDisable(true);

        VBox root = new VBox(10, textArea, copyButton, pasteButton, undoButton);
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Editor de Texto - JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
        */
    	
    	BorderPane root = new BorderPane();
    	root.setCenter(tabPane);
    	
    	MenuBar menuBar = createMenuBar();
    	root.setTop(menuBar);
    	
    	addNewTab();
    	
    	Scene scene = new Scene(root, 800, 600);
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
    	
    	fileMenu.getItems().add(newTabItem);
    	editMenu.getItems().addAll(copyItem,pasteItem,undoItem,redoItem);
    	
    	menuBar.getMenus().addAll(fileMenu,editMenu);
    	return menuBar;
    	
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