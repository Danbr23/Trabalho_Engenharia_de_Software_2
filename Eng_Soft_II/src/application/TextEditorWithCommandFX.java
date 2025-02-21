package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Stack;

// Aplicação JavaFX
public class TextEditorWithCommandFX extends Application {
    private TextArea textArea = new TextArea();
    private Button copyButton = new Button("Copiar");
    private Button pasteButton = new Button("Colar");
    private Button undoButton = new Button("Desfazer");
    private CopyCommand copyCommand;
    private CommandManager commandManager = new CommandManager();

    @Override
    public void start(Stage primaryStage) {
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}