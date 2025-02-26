package decorator;

import org.fxmisc.richtext.CodeArea;

public abstract class SyntaxHighlighterDecorator implements SyntaxHighlighter {
	
	protected SyntaxHighlighter decoratedHighlighter;
	
	public SyntaxHighlighterDecorator(SyntaxHighlighter decoratedHighlighter) {
		this.decoratedHighlighter = decoratedHighlighter;
	}
	
	@Override
	public void applyHighlighting(CodeArea codeArea) {
		decoratedHighlighter.applyHighlighting(codeArea);
	}
	
}
