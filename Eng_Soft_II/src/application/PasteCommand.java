package application;

import org.fxmisc.richtext.CodeArea;

import javafx.scene.control.TextArea;

// Comando Colar
class PasteCommand implements Command {
    private CodeArea codeArea;
    private String previousText;
    private int caretPosition;

    public PasteCommand(CodeArea codeArea) {
        this.codeArea = codeArea;
    }

    @Override
    public void execute() {
        if(codeArea != null) {
        	previousText = codeArea.getText();
        	caretPosition = codeArea.getCaretPosition();
        	codeArea.paste();
        }
    }

    @Override
    public void undo() {
        if(codeArea != null) {
        	codeArea.replaceText(previousText);
        	codeArea.moveTo(caretPosition);
        }
    }
}