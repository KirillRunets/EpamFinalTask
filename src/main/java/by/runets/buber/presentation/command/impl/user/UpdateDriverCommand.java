package by.runets.buber.presentation.command.impl.user;

import by.runets.buber.application.service.user.ReadUserService;
import by.runets.buber.application.service.user.UpdateUserService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.Role;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.LabelParameter;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.infrastructure.util.NumberFormatLocaleFactory;
import by.runets.buber.presentation.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UpdateDriverCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(UpdateDriverCommand.class);
    private UpdateUserService updateUserService;

    public UpdateDriverCommand(UpdateUserService updateUserService) {
        this.updateUserService = updateUserService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        List<User> driverList = null;
        User user = null;

        User logInUser = (User) req.getSession(false).getAttribute(UserRoleType.USER);
        String locale = req.getSession().getAttribute(RequestParameter.LOCALE) == null ? RequestParameter.DEFAULT_LOCALE : req.getSession().getAttribute(RequestParameter.LOCALE).toString();
        String id = req.getParameter(RequestParameter.USER_ID);
        String email = req.getParameter(RequestParameter.EMAIL);
        String firstName = req.getParameter(RequestParameter.FIRST_NAME);
        String secondName = req.getParameter(RequestParameter.SECOND_NAME);
        String birthDate = req.getParameter(RequestParameter.BIRTH_DATE);
        String rating = req.getParameter(RequestParameter.RATING);
        String tripAmount = req.getParameter(RequestParameter.TRIP_AMOUNT);
        String phoneNumber = req.getParameter(RequestParameter.PHONE_NUMBER);
        String role = req.getParameter(RequestParameter.USER_ROLE);
        String numberFormatPattern = NumberFormatLocaleFactory.factory(locale);

        if (RequestValidator.getInstance().isValidateDriverData(id, email, firstName, secondName,birthDate, rating, tripAmount, phoneNumber, role)){
            try {
                user = new User(Integer.parseInt(id), email, firstName, secondName, new SimpleDateFormat(numberFormatPattern).parse(birthDate), Double.parseDouble(rating), Integer.parseInt(tripAmount), phoneNumber, new Role(role));
                updateUserService.update(user);
                if (logInUser.getRole().getRoleName().equalsIgnoreCase(UserRoleType.ADMIN)){
                    driverList = new ReadUserService().find(UserRoleType.DRIVER);
                    if (driverList != null){
                        req.setAttribute(LabelParameter.DRIVER_LIST_LABEL, driverList);
                    }
                    page = JspPagePath.DRIVER_ALL_INFO_PAGE;
                }
                if (logInUser.getRole().getRoleName().equalsIgnoreCase(UserRoleType.DRIVER)){
                    page = JspPagePath.DRIVER_HOME_PAGE;
                }
            } catch (ServiceException | ParseException e) {
                LOGGER.error(e);
            }
        }

        return page;
    }
}
