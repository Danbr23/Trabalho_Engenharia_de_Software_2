package decorator;

import org.fxmisc.richtext.CodeArea;

public interface SyntaxHighlighter { //inteface básica para diferentes highlithers de diferentes linguagens implementarem
	
	void applyHighlighting(CodeArea codeArea);

}
