package by.runets.buber.presentation.command.impl;

import by.runets.buber.application.service.user.LoginUserService;
import by.runets.buber.application.validation.AuthenticationValidator;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.*;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.IOFileException;
import by.runets.buber.infrastructure.util.PropertyFileManager;
import by.runets.buber.presentation.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

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
            PropertyFileManager propertyFileManager = new PropertyFileManager(PropertyPath.CONFIG_FILE);

            if (AuthenticationValidator.getInstance().isValidateLogInData(emailValue, passwordValue)){
                user = Optional.ofNullable(userService.authenticateUser(emailValue, passwordValue));
                if (user.isPresent()){
                    page = loadPageByRole(user.get().getRole().getRoleName());
                } else {
                    req.setAttribute(LabelParameter.ERROR_LABEL, "");
                    page = propertyFileManager.getValue(PropertyKey.LOGIN_PAGE);
                }
            } else {
                req.setAttribute(LabelParameter.ERROR_LABEL, "");
                page = propertyFileManager.getValue(PropertyKey.INDEX_PAGE);
            }
        }  catch (DAOException e) {
            LOGGER.error(e);
        } catch (IOFileException e) {
            LOGGER.fatal(e);
            throw new RuntimeException(e);
        }
        return page;
    }

    private String loadPageByRole(String role) throws IOFileException {
        PropertyFileManager propertyFileManager = new PropertyFileManager(PropertyPath.CONFIG_FILE);
        String page = null;
        switch (role){
            case UserRoleType.ADMIN:
                page = propertyFileManager.getValue(PropertyKey.ADMIN_HOME_PAGE);
                break;
            case UserRoleType.DRIVER:
                page = propertyFileManager.getValue(PropertyKey.DRIVER_HOME_PAGE);
                break;
            case UserRoleType.PASSENGER:
                page = propertyFileManager.getValue(PropertyKey.PASSENGER_HOME_PAGE);
                break;
        }
        return page;
    }
}
