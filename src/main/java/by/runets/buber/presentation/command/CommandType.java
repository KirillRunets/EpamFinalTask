package by.runets.buber.presentation.command;

import by.runets.buber.application.service.user.DeleteUserService;
import by.runets.buber.application.service.user.ReadUserService;
import by.runets.buber.application.service.user.RegisterUserService;
import by.runets.buber.application.service.user.LoginUserService;
import by.runets.buber.presentation.command.impl.ChangeLocaleCommand;
import by.runets.buber.presentation.command.impl.LoginCommand;
import by.runets.buber.presentation.command.impl.LogoutCommand;
import by.runets.buber.presentation.command.impl.SignUpCommand;
import by.runets.buber.presentation.command.impl.user.DeleteUserCommand;
import by.runets.buber.presentation.command.impl.user.ReadDriverCommand;
import by.runets.buber.presentation.command.impl.user.ReadPassengerCommand;

public enum CommandType {
    LOGIN(new LoginCommand(new LoginUserService())),
    SIGNUP(new SignUpCommand(new RegisterUserService())),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    LOGOUT(new LogoutCommand()),
    FIND_ALL_DRIVER(new ReadDriverCommand(new ReadUserService())),
    FIND_ALL_PASSENGER(new ReadPassengerCommand(new ReadUserService())),
    DELETE_USER(new DeleteUserCommand(new DeleteUserService()));

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
