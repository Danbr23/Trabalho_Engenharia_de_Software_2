package command;

import java.util.Stack;

// Gerenciador de Comandos
public class CommandManager {
    private Stack<Command> commandStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>(); 

    public void executeCommand(Command command) {
        command.execute();
        commandStack.push(command);
        redoStack.clear();
    }

    public void undo() {
        if (!commandStack.isEmpty()) {
            Command command = commandStack.pop();
            command.undo();
            redoStack.push(command);
        }
    }

    public void redo() {
        if(!redoStack.isEmpty()) {
        	Command command = redoStack.pop();
        	command.execute();
        	commandStack.push(command);
        }
    }
}