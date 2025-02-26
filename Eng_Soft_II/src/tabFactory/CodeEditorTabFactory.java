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

//Classse responsável por criar uma nova aba do editor de código com superte a numeração de linhas, realce de sintaxe e pesquisa de palavras dentro do código
public class CodeEditorTabFactory implements EditorTabFactory, SearchObserver {
	
	private SearchBar searchBar; //referência para a barra de pesquisa, permitindo que editor reaja às buscas
	private CodeArea codeArea; //área do texto
	
	public CodeEditorTabFactory(SearchBar searchBar) {//Construtor
		this.searchBar = searchBar;
		searchBar.addObserver(this);
	}
		
	@Override
	public Tab createTab() {// método para criar uma nova aba de forma dinâmcica
		
		Tab tab = new Tab("Novo Arquivo"); //toda tab tem o novo de "Novo Arquivo"
		codeArea = new CodeArea(); //cria o espaço onde o usuário irá digitar e o texto será analisado pelo decorator e pelo observer
		codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea)); //comando simples pra ativar a numeração de linhas
		SyntaxHighlighter syntaxHighlighter = new CSyntaxHighlighter(new PlainSyntaxHighlighter()); //cria o decorator que vai realssar as palavras
		
		codeArea.textProperty().addListener((obs, oldText, newText) -> { //sempre que o texto for alterado, o realce de sintaxe será reaplicado
		    syntaxHighlighter.applyHighlighting(codeArea);
		});
		
		
		
		codeArea.setWrapText(true); //habilita quebra de linha automática
		ScrollPane scrollPane = new ScrollPane(codeArea); //container principal com rolagem
		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);
		
		tab.setContent(scrollPane);
		
		return tab;
	}
	
	
	@Override
	public void onSearch(String query) { //método sempre chamado quando uma nova pesquisa é feita na Search Bar.
		//se a pesquisa estiver vazia, remove todos os estilos
		if(query.isEmpty()) {
			codeArea.clearStyle(0,codeArea.getLength());
			return;
		}
		
		//Caso contrário, ele percorre o texto, encontra as ocorrências da palavra e aplica o estilo de destaque
		String text = codeArea.getText();
		int index = 0;
		
		while((index = text.indexOf(query,index)) != -1) {
			int end  = index + query.length();
			codeArea.setStyle(index,end, Collections.singletonList("highlight"));
			index = end;
		}
	}

}
