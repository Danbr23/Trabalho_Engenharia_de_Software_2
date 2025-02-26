package command;

import org.fxmisc.richtext.CodeArea;

import javafx.scene.control.TextArea;

// Comando Copiar
public class CopyCommand implements Command {
    private CodeArea codeArea; //area de texto
    private String copiedText; //texto copiado

    public CopyCommand(CodeArea codeArea) { //construtor
        this.codeArea = codeArea;
    }

    @Override
    public void execute() { //executar o comando
        if(codeArea != null) { // se o campo de texto não estiver vazio obviamente
        	copiedText = codeArea.getSelectedText(); //pega o texto copiado na variavel copiedText
        	codeArea.copy();
        }
    }

    @Override
    public void undo() {
        // Não há como desfazer uma cópia
    }
}