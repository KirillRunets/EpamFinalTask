package by.runets.buber.presentation.command.impl.user;

import by.runets.buber.application.service.user.ChangePasswordService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.*;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.infrastructure.util.LocaleFileManager;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class provides method to implement change password command from driver of passenger.
 */
public class ChangePasswordCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(ChangePasswordCommand.class);
    private ChangePasswordService changePasswordService;

    public ChangePasswordCommand(ChangePasswordService changePasswordService) {
        this.changePasswordService = changePasswordService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        String page = null;

        String locale = req.getSession(false).getAttribute(RequestParameter.LOCALE) == null ? RequestParameter.DEFAULT_LOCALE : req.getSession().getAttribute(RequestParameter.LOCALE).toString();
        String oldPassword = req.getParameter(RequestParameter.OLD_PASSWORD);
        String newPassword = req.getParameter(RequestParameter.NEW_PASSWORD);
        String userId = req.getParameter(RequestParameter.USER_ID);

        if (RequestValidator.getInstance().isValidateChangePasswordData(oldPassword, newPassword, userId)){
            try {
                if (changePasswordService.change(oldPassword, newPassword, new Integer(userId))){
                    page = switchPageByUser(req);
                } else {
                    req.setAttribute(LabelParameter.ERROR_LABEL, LocaleFileManager.getLocale(locale).getProperty(PropertyKey.CHANGE_PASSWORD_ERROR_LABEL));
                    page = switchPageByUser(req);
                }
            } catch (ServiceException e) {
                LOGGER.error(e);
            }
        } else {
            req.setAttribute(LabelParameter.ERROR_LABEL, LocaleFileManager.getLocale(locale).getProperty(PropertyKey.CHANGE_PASSWORD_ERROR_LABEL));
            page = switchPageByUser(req);
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.FORWARD);

        return router;
    }

    private String switchPageByUser(HttpServletRequest req){
        String page = null;
        User user = (User) req.getSession(false).getAttribute(UserRoleType.USER);
        page = user.getRole().getRoleName().equalsIgnoreCase(UserRoleType.DRIVER) ? JspPagePath.DRIVER_PROFILE_PAGE : JspPagePath.DRIVER_PROFILE_PAGE;
        return page;
    }
}
