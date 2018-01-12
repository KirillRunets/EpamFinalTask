package by.runets.buber.presentation.command;

import by.runets.buber.application.service.ban.ReadBanService;
import by.runets.buber.application.service.ban.RevokeBanService;
import by.runets.buber.application.service.ban.SetBanService;
import by.runets.buber.application.service.car.CreateCarService;
import by.runets.buber.application.service.car.DeleteCarService;
import by.runets.buber.application.service.car.EditCarService;
import by.runets.buber.application.service.car.ReadCarService;
import by.runets.buber.application.service.user.*;
import by.runets.buber.presentation.command.impl.*;
import by.runets.buber.presentation.command.impl.ban.DeleteBanCommand;
import by.runets.buber.presentation.command.impl.ban.FillBanFormCommand;
import by.runets.buber.presentation.command.impl.ban.SetBanCommand;
import by.runets.buber.presentation.command.impl.car.*;
import by.runets.buber.presentation.command.impl.user.*;

public enum CommandType {
    LOGIN(new LoginCommand(new LoginUserService())),
    SIGNUP(new SignUpCommand(new RegisterUserService())),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    LOGOUT(new LogoutCommand()),
    FIND_ALL_DRIVER(new ReadDriverCommand(new ReadUserService())),
    FIND_ALL_PASSENGER(new ReadPassengerCommand(new ReadUserService())),
    DELETE_USER(new DeleteUserCommand(new DeleteUserService())),
    LOAD_EDIT_USER(new LoadEditUserPageCommand(new ReadUserService())),
    EDIT_USER(new UpdateUserCommand(new UpdateUserService())),
    HOME_PAGE(new HomePageCommand()),
    BAN_USER(new SetBanCommand(new SetBanService())),
    UNBAN_USER(new DeleteBanCommand(new RevokeBanService())),
    SHOW_BANNED_USERS(new ShowBannedUsersCommand(new ReadBanUserService())),
    FILL_BAN_FORM(new FillBanFormCommand(new ReadBanService())),
    FIND_ALL_VALID_CARS(new ReadValidCarCommand()),
    DELETE_CAR(new DeleteCarCommand(new DeleteCarService())),
    EDIT_CAR(new EditCarCommand(new EditCarService())),
    LOAD_VALID_CAR_TO_EDIT(new LoadValidCarToEditCommand(new ReadCarService())),
    LOAD_VALID_CAR_TO_ADD(new LoadValidCarToAdd()),
    ADD_CAR(new CreateValidCarCommand(new CreateCarService()));

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
