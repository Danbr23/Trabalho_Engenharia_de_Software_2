package command;

import java.util.Stack;

// Gerenciador de Comandos
public class CommandManager {
    private Stack<Command> commandStack = new Stack<>(); //pilha de comandos executados
    private Stack<Command> redoStack = new Stack<>(); //pilha para para guardar comandos que foram defeitos

    public void executeCommand(Command command) { //executa o comando
        command.execute(); // executa a ação do comando
        commandStack.push(command); //poem ele no topo da pilha dos comandos que foram executados
        redoStack.clear();// Limpa a pilha de refazer, pois um novo comando invalida o histórico de redo
    }

    public void undo() { //desfazer
        if (!commandStack.isEmpty()) {  // verifica se há algum comando para desfazer
            Command command = commandStack.pop(); //remove o último comando da pilha
            command.undo(); // desfaz a ação do comando
            redoStack.push(command); // armazena o comando na pilha de refazer
        }
    }

    public void redo() { //refazer o comando
        if(!redoStack.isEmpty()) { //verifica se há comandos para refazer
        	Command command = redoStack.pop(); //remove o comando da pilha de refazer
        	command.execute();//reexecuta o comando
        	commandStack.push(command); //adiciona o comando novamente na pilha de comandos executados
        }
    }
}