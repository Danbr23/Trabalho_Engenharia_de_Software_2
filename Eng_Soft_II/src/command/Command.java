package command;

public interface Command { //interface básica que todo comando deve implementar para ter os metódos executar e desfazer
	void execute();
	void undo();
}
