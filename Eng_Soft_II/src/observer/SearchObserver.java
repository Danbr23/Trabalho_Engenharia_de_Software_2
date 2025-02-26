package observer;

public interface SearchObserver { //interface para qualquer tipo de observer implentar. Sem isso não é observer
	
	void onSearch(String query);

}
