package application;

import javafx.scene.control.TextArea;

// Comando Colar
class PasteCommand implements Command {
    private TextArea textArea;
    private CopyCommand copyCommand;
    private String previousText;

    public PasteCommand(TextArea textArea, CopyCommand copyCommand) {
        this.textArea = textArea;
        this.copyCommand = copyCommand;
    }

    @Override
    public void execute() {
        previousText = textArea.getText();
        if (copyCommand.getClipboard() != null) {
            textArea.insertText(textArea.getCaretPosition(), copyCommand.getClipboard());
        }
    }

    @Override
    public void undo() {
        textArea.setText(previousText);
    }
}