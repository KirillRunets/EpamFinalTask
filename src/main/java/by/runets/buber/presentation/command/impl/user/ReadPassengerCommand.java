package by.runets.buber.presentation.command.impl.user;

import by.runets.buber.application.service.user.ReadUserService;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.presentation.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ReadPassengerCommand implements Command{
    private final static Logger LOGGER = LogManager.getLogger(ReadPassengerCommand.class);
    private ReadUserService readUserService;

    public ReadPassengerCommand(ReadUserService readUserService) {
        this.readUserService = readUserService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        List<User> passengerList = null;

        try {
            passengerList = readUserService.find(UserRoleType.PASSENGER);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        //set list to attribute
        //set page

        return page;
    }
}
