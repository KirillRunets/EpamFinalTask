package by.runets.buber.presentation.command.impl.ban;

import by.runets.buber.application.service.ban.RevokeBanService;
import by.runets.buber.application.service.user.ReadBanUserService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.LabelParameter;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This class provides method to implement revoke ban command by admin.
 */
public class RevokeBanCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(RevokeBanCommand.class);
    private RevokeBanService revokeBanService;

    public RevokeBanCommand(RevokeBanService revokeBanService) {
        this.revokeBanService = revokeBanService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        String page = null;
        List<User> userList = null;

        String userId = req.getParameter(RequestParameter.USER_ID);

        if (RequestValidator.getInstance().isValidate(userId)){
            try {
                if (revokeBanService.revokeBan(new User(Integer.parseInt(userId)))){
                    userList = new ReadBanUserService().read();
                    req.setAttribute(LabelParameter.USER_LIST_LABEL, userList);
                    page = JspPagePath.ALL_BANNED_USERS_PAGE;
                }
            } catch (ServiceException e) {
                LOGGER.error(e);
            }
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.FORWARD);

        return router;
    }
}
