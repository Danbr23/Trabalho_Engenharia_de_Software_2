package decorator;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.fxmisc.richtext.CodeArea;

import java.util.Collections;

//classe específica para destacar as palavras reservadas da linguagem C
public class CSyntaxHighlighter extends SyntaxHighlighterDecorator { //extende do Highlighter padrão
	
	
	//Listas com as palavras reservadas em C, divididas em grupos, cada uma com uma cor diferente.
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
	
	@Override //método para aplicar o highlight
	public void applyHighlighting(CodeArea codeArea) {
		super.applyHighlighting(codeArea);
		
		String text = codeArea.getText();//pega o texto do área de texto
		
		codeArea.clearStyle(0,text.length()); //tira qualquer estilização
		
		//vai verificar a sentença e e comparar com os agrupamentos de palavras para saber com que cor destacar
		highlightWords(codeArea, CONTROL_FLOW, "control-flow");
		highlightWords(codeArea, DATA_TYPES,"data-type");
		highlightWords(codeArea, MODIFIERS, "modifier");
		highlightWords(codeArea, HEADERS, "header");
		
	}
	
	//verifica palavra por palavra da área de texto para adicionar a cor a palavra se ela estiver em algum grupo das palavras reservadas
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
