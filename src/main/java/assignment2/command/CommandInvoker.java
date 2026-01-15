package assignment2.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Deque;

@Slf4j
@Component
public class CommandInvoker {

    private final Deque<OrderCommand> commandHistory = new ArrayDeque<>();
    private final Deque<OrderCommand> redoStack = new ArrayDeque<>();

    public void executeCommand(OrderCommand command) {
        log.debug("Invoking command: {}", command.getCommandName());
        command.execute();
        commandHistory.push(command);
        redoStack.clear();
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            OrderCommand command = commandHistory.pop();
            log.debug("Undoing command: {}", command.getCommandName());
            command.undo();
            redoStack.push(command);
        } else {
            log.warn("No commands to undo");
        }
    }

    public void redoLastCommand() {
        if (!redoStack.isEmpty()) {
            OrderCommand command = redoStack.pop();
            log.debug("Redoing command: {}", command.getCommandName());
            command.execute();
            commandHistory.push(command);
        } else {
            log.warn("No commands to redo");
        }
    }

    public int getHistorySize() {
        return commandHistory.size();
    }
}