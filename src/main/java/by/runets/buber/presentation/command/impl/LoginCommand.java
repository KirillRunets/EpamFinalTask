package by.runets.buber.presentation.command.impl;

import by.runets.buber.application.service.statistic.OrderStatisticService;
import by.runets.buber.application.service.user.LoginUserService;
import by.runets.buber.application.service.user.ReadBanUserService;
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
import javax.servlet.http.HttpSession;

import java.util.*;

/**
 * This class provides method to implement login command.
 */
public class LoginCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    private LoginUserService userService;
    private OrderStatisticService orderStatisticService;

    public LoginCommand(LoginUserService userService, OrderStatisticService orderStatisticService) {
        this.userService = userService;
        this.orderStatisticService = orderStatisticService;
    }

    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        String page = null;
        Optional<User> user;

        String emailValue = req.getParameter(RequestParameter.EMAIL);
        String passwordValue = req.getParameter(RequestParameter.PASSWORD);

        try {
            if (RequestValidator.getInstance().isValidateLogInData(emailValue, passwordValue)){
                user = Optional.ofNullable(userService.authenticateUser(emailValue, passwordValue));
                page = user.isPresent() ? isBanUser(user) ? loadPageByBanUser(req, user) : loadPageByRole(req, user) : loadPageByWrongData(req, emailValue, passwordValue);
            }
        }  catch (ServiceException e) {
            LOGGER.error(e);
        }

        router.setRouteType(Router.RouteType.REDIRECT);
        router.setPagePath(page);


        return router;
    }

    private boolean isBanUser(Optional<User> user) throws ServiceException {
        ReadBanUserService readUserService = new ReadBanUserService();
        List<User> userList = readUserService.read();
        return userList.stream()
                .anyMatch(u -> user.isPresent() && u.getId() == user.get().getId());
    }


    private String loadPageByRole(HttpServletRequest request, Optional<User> user) throws ServiceException {
        String page = null;
        HttpSession httpSession = request.getSession();

        if (user.isPresent()){
            switch (user.get().getRole().getRoleName()){
                case UserRoleType.ADMIN:
                    httpSession.setAttribute(UserRoleType.USER, user.get());
                    page = JspPagePath.ADMIN_HOME_PAGE;
                    break;
                case UserRoleType.DRIVER:
                    httpSession.setAttribute(UserRoleType.USER, user.get());
                    page = JspPagePath.DRIVER_HOME_PAGE;
                    break;
                case UserRoleType.PASSENGER:
                    httpSession.setAttribute(UserRoleType.USER, user.get());
                    page = JspPagePath.PASSENGER_HOME_PAGE;
                    break;
            }
            List<Integer> data = orderStatisticService.collectStats(user.get().getId(), user.get().getRole().getRoleName());
            httpSession.setAttribute(LabelParameter.TRIP_AMOUNT_STATISTICS, data);
        }

        return page;
    }

    private String  loadPageByBanUser(HttpServletRequest req, Optional<User> user){
        String page = null;

        if (user.isPresent()){
            req.getSession().setAttribute(RequestParameter.UNBAN_DATE, user.get().getUnBaneDate());
            req.getSession().setAttribute(RequestParameter.BAN_DESCRIPTION, user.get().getBan().getBanDescription());
            page = JspPagePath.ERROR_USER_NOTIFICATION;;
        }

        return page;
    }

    private String loadPageByWrongData(HttpServletRequest req, String emailValue, String passwordValue){
        String locale = req.getSession().getAttribute(RequestParameter.LOCALE) == null ? RequestParameter.DEFAULT_LOCALE : req.getSession().getAttribute(RequestParameter.LOCALE).toString();
        req.getSession().setAttribute(LabelParameter.ERROR_LABEL, LocaleFileManager.getLocale(locale).getProperty(PropertyKey.LOGIN_ERROR_LABEL_MESSAGE));
        req.getSession().setAttribute(LabelParameter.EMAIL, emailValue);
        req.getSession().setAttribute(LabelParameter.PASSWORD, passwordValue);
        return JspPagePath.LOGIN_PAGE;
    }
}

