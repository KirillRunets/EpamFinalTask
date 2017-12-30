package by.runets.buber.presentation.command;

import by.runets.buber.application.service.user.LoginUserService;
import by.runets.buber.presentation.command.impl.LoginCommand;

public enum CommandType {
    LOGIN(new LoginCommand(new LoginUserService())),
    SIGNUP,
    LOGOUT;

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    CommandType() {
    }

    public Command getCommand() {
        return command;
    }
}
