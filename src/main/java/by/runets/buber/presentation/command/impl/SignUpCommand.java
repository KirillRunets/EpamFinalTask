package by.runets.buber.presentation.command.impl;

import by.runets.buber.application.service.user.RegisterUserService;
import by.runets.buber.application.validation.AuthenticationValidator;
import by.runets.buber.infrastructure.constant.*;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.util.LocaleFileManager;
import by.runets.buber.presentation.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SignUpCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(SignUpCommand.class);
    private RegisterUserService userService;

    public SignUpCommand(RegisterUserService createUserService) {
        userService = createUserService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;

        String email = req.getParameter(CommandParameter.PARAM_NAME_EMAIL);
        String password = req.getParameter(CommandParameter.PARAM_NAME_PASSWORD);
        String firstName = req.getParameter(CommandParameter.PARAM_NAME_FIRST_NAME);
        String secondName = req.getParameter(CommandParameter.PARAM_NAME_SECOND_NAME);
        String role = req.getParameter(CommandParameter.PARAM_NAME_ROLE);

        try {
            if (AuthenticationValidator.getInstance().isValidateRegisterData(email, password, firstName, secondName, role)){
                if (userService.registerUser(email, password, firstName, secondName, role)){
                    page = JspPagePath.LOGIN_PAGE;
                }
            }
        } catch (DAOException e) {
            LOGGER.error(e);
            req.setAttribute(LabelParameter.ERROR_LABEL, new LocaleFileManager(PropertyPath.SIGN_UP_PROPERTIES_FILE, req.getLocale()).getProperty(PropertyKey.ERROR_LABEL_MESSAGE));
            page = JspPagePath.SIGN_UP_PAGE;
        }

        return page;
    }
}
