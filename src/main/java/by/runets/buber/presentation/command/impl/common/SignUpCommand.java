package by.runets.buber.presentation.command.impl.common;

import by.runets.buber.application.service.user.RegisterUserService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.Role;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.*;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.infrastructure.util.LocaleFileManager;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * This class provides method to implement sign up command.
 */
public class SignUpCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(SignUpCommand.class);
    private RegisterUserService userService;
    public SignUpCommand(RegisterUserService createUserService) {
        userService = createUserService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();

        String locale = req.getSession(false).getAttribute(RequestParameter.LOCALE) == null ? RequestParameter.DEFAULT_LOCALE : req.getSession().getAttribute(RequestParameter.LOCALE).toString();
        User user = null;
        try {
            user = init(req);
            router = user != null && userService.registerUser(user)
                    ? rightRoute()
                    : errorRoute(req, LabelParameter.ERROR_LABEL, LocaleFileManager.getLocale(locale).getProperty(PropertyKey.SIGN_UP_ERROR_LABEL_MESSAGE), user);
        } catch (ServiceException e) {
            LOGGER.error(e);
        } catch (AuthenticationException e) {
            router = errorRoute(req, LabelParameter.ERROR_LABEL, LocaleFileManager.getLocale(locale).getProperty(PropertyKey.EMAIL_EXIST_LABEL_MESSAGE), user);
            LOGGER.error(e);
        }

        return router;
    }

    private User init(HttpServletRequest req){
        String email = req.getParameter(RequestParameter.EMAIL);
        String password = req.getParameter(RequestParameter.PASSWORD);
        String firstName = req.getParameter(RequestParameter.FIRST_NAME);
        String secondName = req.getParameter(RequestParameter.SECOND_NAME);
        String role = req.getParameter(RequestParameter.USER_ROLE);
        User user = null;

        if (RequestValidator.getInstance().isValidateRegisterData(email, password, firstName, secondName, role)){
            user = new User(email, password, firstName, secondName, new Role(role));
        }

        return user;
    }

    private Router rightRoute(){
        Router router = new Router();

        router.setPagePath(JspPagePath.LOGIN_PAGE);
        router.setRouteType(Router.RouteType.REDIRECT);

        return router;
    }

    private Router errorRoute(HttpServletRequest req, String label, String message, User user){
        Router router = new Router();

        req.setAttribute(label, message);
        req.setAttribute(UserRoleType.USER, user);

        router.setRouteType(Router.RouteType.FORWARD);
        router.setPagePath(JspPagePath.SIGN_UP_PAGE);

        return router;
    }
}


