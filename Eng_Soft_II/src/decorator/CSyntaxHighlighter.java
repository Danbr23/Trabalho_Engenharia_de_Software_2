package decorator;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.fxmisc.richtext.CodeArea;

import java.util.Collections;

public class CSyntaxHighlighter extends SyntaxHighlighterDecorator {
	
	private static final List<String> DATA_TYPES = Arrays.asList("char","double","enum","float","int","long","short",
			"signed","struct","union","unsigned","void");
	
	private static final List<String> CONTROL_FLOW = Arrays.asList("break","case","continue","default","do","else",
			"for","goto","if","return","switch","while");
	
	private static final List<String> MODIFIERS = Arrays.asList("auto","const","extern","register",
			"sizeof","static","typedef","volatile");
	
	private static final List<String> HEADERS = Arrays.asList("define","include");

	
	public CSyntaxHighlighter(SyntaxHighlighter decoratedHighlighter) {
		super(decoratedHighlighter);
	}
	
	@Override
	public void applyHighlighting(CodeArea codeArea) {
		super.applyHighlighting(codeArea);
		
		String text = codeArea.getText();
		
		codeArea.clearStyle(0,text.length());
		
		highlightWords(codeArea, CONTROL_FLOW, "control-flow");
		highlightWords(codeArea, DATA_TYPES,"data-type");
		highlightWords(codeArea, MODIFIERS, "modifier");
		highlightWords(codeArea, HEADERS, "header");
		
	}
	
	private void highlightWords(CodeArea codeArea, List<String> words, String styleClass) {
		for(String word : words) {
			Pattern pattern = Pattern.compile("\\b" + word + "\\b");
			Matcher matcher = pattern.matcher(codeArea.getText());
			
			while(matcher.find()) {
			
				codeArea.setStyle(matcher.start(),matcher.end(),Collections.singletonList(styleClass));
			}
		}
	}

}
