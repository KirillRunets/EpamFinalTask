package by.runets.buber.presentation.command;

import java.util.Optional;

public class ActionFactory {
    public static Optional<Command> defineCommand(String commandType){
        Optional<Command> current = Optional.empty();
        if (commandType == null){
            return current;
        }

        CommandType type = CommandType.valueOf(commandType.toUpperCase());
        current = Optional.of(type.getCommand());

        return current;
    }
}
