package by.runets.buber.presentation.command.impl.user;

import by.runets.buber.application.service.user.ReadBanUserService;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.LabelParameter;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowBannedUsersCommand implements Command{
    private final static Logger LOGGER = LogManager.getLogger(ShowBannedUsersCommand.class);
    private ReadBanUserService readBanUserService;

    public ShowBannedUsersCommand(ReadBanUserService readBanUserService) {
        this.readBanUserService = readBanUserService;
    }

    @Override
    public Router execute(HttpServletRequest req) {
        Router router = new Router();
        String page = null;
        List<User> userList = null;
        try {
            userList = readBanUserService.read();
            req.getSession().setAttribute(LabelParameter.USER_LIST_LABEL, userList);
            page = JspPagePath.ALL_BANNED_USERS_PAGE;
        } catch (ServiceException e) {
            LOGGER.error(e);
        }
        router.setPagePath(page);
        router.setRouteType(Router.RouteType.REDIRECT);

        return router;
    }
}
