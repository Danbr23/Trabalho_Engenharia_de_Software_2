package observer;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class SearchBarUI {
	private SearchBar searchBar;
	
	public SearchBarUI(SearchBar searchBar) {
		this.searchBar = searchBar;
	}
	
	public HBox createSearchBar() {
		TextField searchField  = new TextField();
		searchField.setPromptText("Pesquisar...");
		
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			searchBar.setQuery(newValue);
		});
		
		return new HBox(searchField);
	}

}
