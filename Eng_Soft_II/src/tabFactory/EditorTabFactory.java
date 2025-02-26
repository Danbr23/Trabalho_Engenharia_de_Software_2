package tabFactory;

import javafx.scene.control.Tab;

public interface EditorTabFactory { //interface básica do command pattern Factory para criar componentes
	
	Tab createTab();
}
