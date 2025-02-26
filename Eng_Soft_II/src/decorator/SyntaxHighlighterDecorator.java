package decorator;

import org.fxmisc.richtext.CodeArea;

//classe abstrata que implementa o padrão Decorator para realce de sintaxe
public abstract class SyntaxHighlighterDecorator implements SyntaxHighlighter {
	
	//referência para o objeto que será decorado (outro SyntaxHighLighter)
	protected SyntaxHighlighter decoratedHighlighter;
	
	//Construtor
	public SyntaxHighlighterDecorator(SyntaxHighlighter decoratedHighlighter) {
		this.decoratedHighlighter = decoratedHighlighter;
	}
	
	//Método que aplica o realce de sintaxe, delegando a execução ao objeto decorado
	@Override
	public void applyHighlighting(CodeArea codeArea) {
		decoratedHighlighter.applyHighlighting(codeArea);
	}
	
}
