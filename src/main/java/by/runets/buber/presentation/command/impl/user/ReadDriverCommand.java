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

public class ReadDriverCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(ReadDriverCommand.class);
    private ReadUserService readUserService;

    public ReadDriverCommand(ReadUserService readUserService) {
        this.readUserService = readUserService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        List<User> driverList = null;

        try {
            driverList = readUserService.find(UserRoleType.DRIVER);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        //set list to attribute
        //set page

        return page;
    }
}
