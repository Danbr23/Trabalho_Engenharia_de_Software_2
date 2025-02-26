package command;

import org.fxmisc.richtext.CodeArea;

import javafx.scene.control.TextArea;

// Comando Colar
public class PasteCommand implements Command {
    private CodeArea codeArea; //area do texto
    private String previousText; //guarda o estado anterior do texto antes de colar
    private int caretPosition; //posicao do cursor anes da ação de colar

    public PasteCommand(CodeArea codeArea) { //construtor
        this.codeArea = codeArea;
    }

    @Override
    public void execute() { //executar a acao
        if(codeArea != null) { 
        	previousText = codeArea.getText(); //armazena  o texto atual antes de colar
        	caretPosition = codeArea.getCaretPosition(); //salva a posição do cursor antes de colar
        	codeArea.paste(); //realiza a ação de colar o conteúdo da área de transferência
        }
    }

    @Override //desfazer o comando colar, restaurando o texto anterior
    public void undo() {
        if(codeArea != null) {
        	codeArea.replaceText(previousText); //restaura o texto original
        	codeArea.moveTo(caretPosition); //retorna o cursor para a posição original
        }
    }
}