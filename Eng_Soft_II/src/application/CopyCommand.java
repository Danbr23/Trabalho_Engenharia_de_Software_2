package application;

import javafx.scene.control.TextArea;

// Comando Copiar
class CopyCommand implements Command {
    private TextArea textArea;
    private String clipboard;

    public CopyCommand(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void execute() {
        clipboard = textArea.getSelectedText();
        System.out.println("Copiado: " + clipboard);
    }

    @Override
    public void undo() {
        System.out.println("Undo de Copiar não faz nada.");
    }

    public String getClipboard() {
        return clipboard;
    }
}