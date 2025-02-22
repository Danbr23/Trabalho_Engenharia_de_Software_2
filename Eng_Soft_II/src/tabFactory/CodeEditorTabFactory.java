package tabFactory;

import org.fxmisc.richtext.CodeArea;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;

public class CodeEditorTabFactory implements EditorTabFactory {
	
	@Override
	public Tab createTab() {
		Tab tab = new Tab("Novo Arquivo");
		CodeArea codeArea = new CodeArea();
		
		codeArea.setWrapText(true);
		ScrollPane scrollPane = new ScrollPane(codeArea);
		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);
		
		tab.setContent(scrollPane);
		
		return tab;
	}

}
