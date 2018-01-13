package by.runets.buber.presentation.command.impl;

import by.runets.buber.application.service.user.LoginUserService;
import by.runets.buber.application.service.user.ReadBanUserService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.*;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.infrastructure.util.LocaleFileManager;
import by.runets.buber.presentation.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;
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
            if (RequestValidator.getInstance().isValidateLogInData(emailValue, passwordValue)){
                user = Optional.ofNullable(userService.authenticateUser(emailValue, passwordValue));
                page = user.isPresent() ? isBanUser(user) ? loadPageByBanUser(req, user) : loadPageByRole(req, user) : loadPageByWrongData(req, emailValue, passwordValue);
            }
        }  catch (DAOException | ServiceException e) {
            LOGGER.error(e);
        }
        return page;
    }

    private boolean isBanUser(Optional<User> user) throws ServiceException {
        ReadBanUserService readUserService = new ReadBanUserService();
        List<User> userList = readUserService.read();
        return userList.stream()
                .anyMatch(u -> user.isPresent() && u.getId() == user.get().getId());
    }


    private String loadPageByRole(HttpServletRequest request, Optional<User> user) {
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
        }

        return page;
    }

    private String loadPageByBanUser(HttpServletRequest req, Optional<User> user){
        String page = null;

        if (user.isPresent()){
            req.setAttribute(RequestParameter.UNBAN_DATE, user.get().getUnBaneDate());
            req.setAttribute(RequestParameter.BAN_DESCRIPTION, user.get().getBan().getBanDescription());
            page = JspPagePath.ERROR_USER_NOTIFICATION;;
        }

        return page;
    }

    private String loadPageByWrongData(HttpServletRequest req, String emailValue, String passwordValue){
        String locale = req.getSession().getAttribute(RequestParameter.LOCALE) == null ? RequestParameter.DEFAULT_LOCALE : req.getSession().getAttribute(RequestParameter.LOCALE).toString();
        req.setAttribute(LabelParameter.ERROR_LABEL, LocaleFileManager.getLocale(locale).getProperty(PropertyKey.LOGIN_ERROR_LABEL_MESSAGE));
        req.setAttribute(LabelParameter.EMAIL, emailValue);
        req.setAttribute(LabelParameter.PASSWORD, passwordValue);
        return JspPagePath.LOGIN_PAGE;
    }
}

