package by.runets.buber.presentation.command;

import java.util.Optional;

public class ActionFactory {
    /**
     * Define command from request parameter, if commandType is exist return special object else empty optional.
     * @param commandType
     * @return
     */
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
