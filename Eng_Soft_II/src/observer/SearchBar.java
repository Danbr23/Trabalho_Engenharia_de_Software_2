package observer;

import java.util.ArrayList;
import java.util.List;


public class SearchBar {

	private List<SearchObserver> observers = new ArrayList<>();
	private String query = "";
	
	public void addObserver(SearchObserver observer) {
		observers.add(observer);
	}
	
	public void removeObserver(SearchObserver observer) {
		observers.remove(observer);
	}
	
	public void setQuery(String query) {
		this.query = query;
		notifyObservers();
	}
	
	private void notifyObservers() {
		for(SearchObserver observer : observers) {
			observer.onSearch(query);
		}
	}
}
