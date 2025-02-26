package tabFactory;

import java.util.Collections;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import tabFactory.*;
import observer.*;
import decorator.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.paint.Color;
import org.fxmisc.richtext.StyleClassedTextArea;

public class CodeEditorTabFactory implements EditorTabFactory, SearchObserver {
	
	private SearchBar searchBar;
	private CodeArea codeArea;
	
	public CodeEditorTabFactory(SearchBar searchBar) {
		this.searchBar = searchBar;
		searchBar.addObserver(this);
	}
	
	@Override
	public Tab createTab() {
		
		Tab tab = new Tab("Novo Arquivo");
		codeArea = new CodeArea();
		codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
		SyntaxHighlighter syntaxHighlighter = new CSyntaxHighlighter(new PlainSyntaxHighlighter());
		
		codeArea.textProperty().addListener((obs, oldText, newText) -> {
		    syntaxHighlighter.applyHighlighting(codeArea);
		});
		
		
		
		codeArea.setWrapText(true);
		ScrollPane scrollPane = new ScrollPane(codeArea);
		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);
		
		tab.setContent(scrollPane);
		
		return tab;
	}
	
	
	@Override
	public void onSearch(String query) {
		if(query.isEmpty()) {
			codeArea.clearStyle(0,codeArea.getLength());
			return;
		}
		
		String text = codeArea.getText();
		int index = 0;
		
		while((index = text.indexOf(query,index)) != -1) {
			int end  = index + query.length();
			codeArea.setStyle(index,end, Collections.singletonList("highlight"));
			index = end;
		}
	}

}
