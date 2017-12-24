package command;

import exception.AuthenticationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;
import validation.AuthenticationValidator;

import javax.servlet.http.HttpServletRequest;

import static util.ConfigurationManager.getBundle;

public class LoginCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    private final static String PARAM_NAME_EMAIL = "email";
    private final static String PARAM_NAME_PASSWORD = "password";
    private final static String CONFIG_INDEX_KEY = "path.page.index";
    private final static String CONFIG_LOGIN_KEY = "path.page.login";
    private final static String ERROR_LABEL = "errorLoginPasswordMessage";
    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    public String execute(HttpServletRequest req) {
        String page = null;
        String emailValue = req.getParameter(PARAM_NAME_EMAIL);
        String passwordValue = req.getParameter(PARAM_NAME_PASSWORD);
        try {
            if (AuthenticationValidator.getInstance().isLogInData(emailValue, passwordValue)){
                if (userService.checkUser()){
                    page = getBundle(CONFIG_INDEX_KEY);
                } else {
                    req.setAttribute(ERROR_LABEL, "");
                    page = getBundle(CONFIG_LOGIN_KEY);
                }
            } else {
                req.setAttribute(ERROR_LABEL, "");
                page = getBundle(CONFIG_LOGIN_KEY);
            }
        } catch (AuthenticationException e) {
            LOGGER.error(e);
        }
        return page;
    }
}
