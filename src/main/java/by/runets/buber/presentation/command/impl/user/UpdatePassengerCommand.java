package by.runets.buber.presentation.command.impl.user;

import by.runets.buber.application.service.user.UpdateUserService;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.presentation.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UpdatePassengerCommand implements Command{
    private final static Logger LOGGER = LogManager.getLogger(UpdateUserService.class);
    private UpdateUserService updateUserService;

    public UpdatePassengerCommand(UpdateUserService updateUserService) {
        this.updateUserService = updateUserService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;

        User logInUser = (User) req.getSession(false).getAttribute(UserRoleType.USER);
        User user = (User) req.getSession(false).getAttribute(RequestParameter.MODIFIED_PASSENGER);

        if (user != null){
            try {
                updateUserService.update(user);
                page = logInUser.getRole().getRoleName().equalsIgnoreCase(UserRoleType.ADMIN) ? JspPagePath.PASSENGER_ALL_INFO_PAGE : JspPagePath.PASSENGER_HOME_PAGE;
            } catch (ServiceException e) {
                LOGGER.error(e);
            }
        }

        return page;
    }
}
