package by.runets.buber.presentation.command;

import by.runets.buber.application.service.user.CreateUserService;
import by.runets.buber.application.service.user.LoginUserService;
import by.runets.buber.presentation.command.impl.ChangeLocaleCommand;
import by.runets.buber.presentation.command.impl.LoginCommand;
import by.runets.buber.presentation.command.impl.LogoutCommand;
import by.runets.buber.presentation.command.impl.SignUpCommand;

public enum CommandType {
    LOGIN(new LoginCommand(new LoginUserService())),
    SIGNUP(new SignUpCommand(new CreateUserService())),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    LOGOUT(new LogoutCommand());

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
