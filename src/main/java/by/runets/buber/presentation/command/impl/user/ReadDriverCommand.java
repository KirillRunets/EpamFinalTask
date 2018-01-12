package by.runets.buber.presentation.command.impl.user;

import by.runets.buber.application.service.user.ReadUserService;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.LabelParameter;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.presentation.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

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
            if (driverList != null){
                req.setAttribute(LabelParameter.DRIVER_LIST_LABEL, driverList);
                page = JspPagePath.DRIVER_ALL_INFO_PAGE;
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        return page;
    }

    /*private void checkAuthorizedUser(HttpServletRequest req){
        User user = req.getSession(false).getAttribute(UserRoleType)
    }*/
}
