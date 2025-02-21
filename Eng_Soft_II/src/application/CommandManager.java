package application;

import java.util.Stack;

// Gerenciador de Comandos
class CommandManager {
    private Stack<Command> history = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        history.push(command);
    }

    public void undoLastCommand() {
        if (!history.isEmpty()) {
            Command lastCommand = history.pop();
            lastCommand.undo();
        }
    }

    public boolean hasHistory() {
        return !history.isEmpty();
    }
}