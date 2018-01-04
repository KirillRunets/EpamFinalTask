package by.runets.buber.presentation.command;

import by.runets.buber.application.service.user.*;
import by.runets.buber.presentation.command.impl.*;
import by.runets.buber.presentation.command.impl.user.DeleteUserCommand;
import by.runets.buber.presentation.command.impl.user.ReadDriverCommand;
import by.runets.buber.presentation.command.impl.user.ReadPassengerCommand;
import by.runets.buber.presentation.command.impl.user.UpdateDriverCommand;

public enum CommandType {
    LOGIN(new LoginCommand(new LoginUserService())),
    SIGNUP(new SignUpCommand(new RegisterUserService())),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    LOGOUT(new LogoutCommand()),
    FIND_ALL_DRIVER(new ReadDriverCommand(new ReadUserService())),
    FIND_ALL_PASSENGER(new ReadPassengerCommand(new ReadUserService())),
    DELETE_USER(new DeleteUserCommand(new DeleteUserService())),
    LOAD_EDIT_DRIVER(new LoadEditDriverPageCommand(new ReadUserService())),
    UPDATE_DRIVER(new UpdateDriverCommand(new UpdateUserService())),
    HOME_PAGE(new HomePageCommand());

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
