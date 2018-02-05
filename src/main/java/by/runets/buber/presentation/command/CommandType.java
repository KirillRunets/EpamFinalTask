package by.runets.buber.presentation.command;

import by.runets.buber.application.service.ban.*;
import by.runets.buber.application.service.bonus.*;
import by.runets.buber.application.service.car.CreateCarService;
import by.runets.buber.application.service.car.DeleteCarService;
import by.runets.buber.application.service.car.EditCarService;
import by.runets.buber.application.service.car.ReadCarService;
import by.runets.buber.application.service.order.*;
import by.runets.buber.application.service.statistic.OrderStatisticService;
import by.runets.buber.application.service.statistic.RatingStatisticService;
import by.runets.buber.application.service.transaction.ReadTransactionService;
import by.runets.buber.application.service.transaction.RollbackTransactionService;
import by.runets.buber.application.service.user.*;
import by.runets.buber.presentation.command.impl.*;
import by.runets.buber.presentation.command.impl.ban.*;
import by.runets.buber.presentation.command.impl.bonus.*;
import by.runets.buber.presentation.command.impl.car.*;
import by.runets.buber.presentation.command.impl.load.*;
import by.runets.buber.presentation.command.impl.order.*;
import by.runets.buber.presentation.command.impl.transaction.RollbackTransactionCommand;
import by.runets.buber.presentation.command.impl.transaction.ShowTransactionCommand;
import by.runets.buber.presentation.command.impl.user.*;

/**
 * This enum contains attributes which provides special command class and service.
 */
public enum CommandType {
    LOGIN(new LoginCommand(new LoginUserService(), new OrderStatisticService())),
    SIGNUP(new SignUpCommand(new RegisterUserService())),
    LOAD_SIGN_UP_PAGE(new LoadSignUpPageCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    LOGOUT(new LogoutCommand()),
    FIND_ALL_DRIVER(new ReadDriverCommand(new ReadUserService())),
    FIND_ALL_PASSENGER(new ReadPassengerCommand(new ReadUserService())),
    DELETE_USER(new DeleteUserCommand(new DeleteUserService())),
    LOAD_EDIT_USER(new LoadEditUserPageCommand(new ReadUserService())),
    EDIT_USER(new UpdateUserCommand(new UpdateUserService())),
    HOME_PAGE(new LoadHomePageCommand()),
    BAN_USER(new SetBanCommand(new SetBanService())),
    UNBAN_USER(new RevokeBanCommand(new RevokeBanService())),
    SHOW_BANNED_USERS(new ShowBannedUsersCommand(new ReadBanUserService())),
    FILL_BAN_FORM(new FillBanFormCommand(new ReadBanService())),
    FILL_BONUS_FORM(new FillBonusFormCommand(new ReadBonusService())),
    CREATE_BAN(new CreateBanCommand(new CreateBanService())),
    DELETE_BAN(new DeleteBanCommand(new DeleteBanService())),
    UPDATE_BAN(new UpdateBanCommand(new UpdateBanService())),
    LOAD_BAN_FORM(new LoadBanFormCommand(new ReadBanService())),
    LOAD_BONUS_FORM(new LoadBonusFormCommand(new ReadBonusService())),
    SHOW_BAN(new ReadBanCommand(new ReadBanService())),
    FIND_ALL_VALID_CARS(new ReadCarCommand(new ReadCarService())),
    DELETE_CAR(new DeleteCarCommand(new DeleteCarService())),
    EDIT_CAR(new UpdateCarCommand(new EditCarService())),
    LOAD_VALID_CAR_TO_EDIT(new LoadEditCarPageCommand(new ReadCarService())),
    LOAD_VALID_CAR_TO_ADD(new LoadValidCarPage()),
    ADD_CAR(new CreateCarCommand(new CreateCarService())),
    CHANGE_PASSWORD(new ChangePasswordCommand(new ChangePasswordService())),
    MAKE_ORDER(new MakeOrderCommand(new MakeOrderService())),
    CALCULATE_ORDER_DATA(new CalculateOrderDataCommand(new CalculateOrderDataService(), new CollectDriversToOrderService())),
    CONFIRM_ORDER(new ConfirmOrderCommand(new ConfirmOrderService())),
    REVOKE_ORDER(new RevokeOrderCommand(new RevokeOrderService())),
    COMPLETE_ORDER(new CompleteOrderCommand(new CompleteOrderService())),
    PAY_ORDER(new PayOrderCommand(new PayOrderService())),
    RATE_USER(new RateUserCommand(new RatingStatisticService())),
    SHOW_ORDER(new ShowOrderCommand(new ReadOrderService())),
    SHOW_TRANSACTION(new ShowTransactionCommand(new ReadTransactionService())),
    ROLLBACK_TRANSACTION(new RollbackTransactionCommand(new RollbackTransactionService())),
    CREATE_BONUS(new CreateBonusCommand(new CreateBonusService())),
    DELETE_BONUS(new DeleteBonusCommand(new DeleteBonusService())),
    UPDATE_BONUS(new UpdateBonusCommand(new UpdateBonusService())),
    SHOW_BONUS(new ReadBonusCommand(new ReadBonusService())),
    SET_BONUS(new SetBonusCommand(new SetBonusService()));

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
