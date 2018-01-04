package by.runets.buber.presentation.command.impl;

import by.runets.buber.application.service.user.LoginUserService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.*;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.util.LocaleFileManager;
import by.runets.buber.presentation.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Optional;


public class LoginCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    private LoginUserService userService;

    public LoginCommand(LoginUserService userService) {
        this.userService = userService;
    }

    public String execute(HttpServletRequest req) {
        String page = null;
        Optional<User> user;

        String emailValue = req.getParameter(CommandParameter.PARAM_NAME_EMAIL);
        String passwordValue = req.getParameter(CommandParameter.PARAM_NAME_PASSWORD);

        try {
            user = Optional.ofNullable(userService.authenticateUser(emailValue, passwordValue));
            if (RequestValidator.getInstance().isValidateLogInData(emailValue, passwordValue)){
                if (user.isPresent()){
                    page = loadPageByRole(req, user);
                } else {
                    String locale = req.getSession().getAttribute(RequestParameter.LOCALE) == null ? RequestParameter.DEFAULT_LOCALE : req.getSession().getAttribute(RequestParameter.LOCALE).toString();
                    req.setAttribute(LabelParameter.ERROR_LABEL, LocaleFileManager.getLocale(locale).getProperty(PropertyKey.LOGIN_ERROR_LABEL_MESSAGE));
                    page = JspPagePath.LOGIN_PAGE;
                }
            }
        }  catch (DAOException e) {
            LOGGER.error(e);
        }
        return page;
    }

    private String loadPageByRole(HttpServletRequest request, Optional<User> user) {
        String page = null;
        HttpSession httpSession = request.getSession();
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
        return page;
    }

}
