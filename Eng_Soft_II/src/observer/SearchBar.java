package observer;

import java.util.ArrayList;
import java.util.List;

//classe responsável por gerenciar a barra de pesquisa en notificar os observadores sobre mudanças
public class SearchBar {

	private List<SearchObserver> observers = new ArrayList<>(); //lista de observadores (objetos que reagem à mudança na pesquisa)
	private String query = ""; //armazena a consulta de pesquisa atual
	
	//adicionar observador à lista
	public void addObserver(SearchObserver observer) {
		observers.add(observer);
	}
	//remover observador da lista
	public void removeObserver(SearchObserver observer) {
		observers.remove(observer);
	}
	//atualiza a pesquisa e notifica os observadores sobre a mudança
	public void setQuery(String query) {
		this.query = query;
		notifyObservers();
	}
	//percorre a lista de observadores e chama o método onSearch para cada um 
	private void notifyObservers() {
		for(SearchObserver observer : observers) {
			observer.onSearch(query);
		}
	}
}
