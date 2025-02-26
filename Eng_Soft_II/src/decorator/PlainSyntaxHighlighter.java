package decorator;

import org.fxmisc.richtext.CodeArea;

public class PlainSyntaxHighlighter implements SyntaxHighlighter {
	
	@Override
	public void applyHighlighting(CodeArea codeArea) {
		//Sem realce de sintaxe (texto simples)
		codeArea.clearStyle(0, codeArea.getLength());
	}

}
