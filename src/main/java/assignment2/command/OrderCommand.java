package assignment2.command;

public interface OrderCommand {
    void execute();
    void undo();
    String getCommandName();
}