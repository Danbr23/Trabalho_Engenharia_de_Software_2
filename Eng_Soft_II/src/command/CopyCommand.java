package command;

import org.fxmisc.richtext.CodeArea;

import javafx.scene.control.TextArea;

// Comando Copiar
public class CopyCommand implements Command {
    private CodeArea codeArea;
    private String copiedText;

    public CopyCommand(CodeArea codeArea) {
        this.codeArea = codeArea;
    }

    @Override
    public void execute() {
        if(codeArea != null) {
        	copiedText = codeArea.getSelectedText();
        	codeArea.copy();
        }
    }

    @Override
    public void undo() {
        // Não há como desfazer uma cópia
    }
}