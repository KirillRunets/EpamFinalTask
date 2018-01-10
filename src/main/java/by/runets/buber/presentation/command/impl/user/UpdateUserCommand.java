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
import java.util.List;

public class UpdateUserCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(UpdateUserCommand.class);
    private UpdateUserService updateUserService;

    public UpdateUserCommand(UpdateUserService updateUserService) {
        this.updateUserService = updateUserService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;

        User logInUser = (User) req.getSession(false).getAttribute(UserRoleType.USER);
        boolean isAdmin = logInUser.getRole().getRoleName().equalsIgnoreCase(UserRoleType.ADMIN);

        try {
            User user = init(req);
            if (user != null){
                updateUserService.update(user);
                page = isAdmin ? switchPageByAdmin(user.getRole().getRoleName(), req) : switchPageByUser(user.getRole().getRoleName(), req);
            }
        } catch (ServiceException | ParseException e) {
            LOGGER.error(e);
        }

        return page;
    }

    private String switchPageByAdmin(String role, HttpServletRequest req) throws ServiceException {
        String page = null;
        switch (role){
            case UserRoleType.DRIVER:
                page = setDataToPage(req, UserRoleType.DRIVER, LabelParameter.DRIVER_LIST_LABEL, true);
                break;
            case UserRoleType.PASSENGER:
                page = setDataToPage(req, UserRoleType.PASSENGER, LabelParameter.PASSENGER_LIST_LABEL, true);
                break;
        }
        return page;
    }

    private String switchPageByUser(String role, HttpServletRequest req) throws ServiceException {
        String page = null;
        switch (role){
            case UserRoleType.DRIVER:
                page = setDataToPage(req, UserRoleType.DRIVER, LabelParameter.DRIVER, false);
                break;
            case UserRoleType.PASSENGER:
                page = setDataToPage(req, UserRoleType.PASSENGER, LabelParameter.PASSENGER, false);
                break;
        }
        return page;
    }

    private User init(HttpServletRequest req) throws ParseException {
        User user = null;
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

        if (RequestValidator.getInstance().isValidateDriverData(id, email, firstName, secondName, birthDate, rating, tripAmount, phoneNumber, role)) {
            user = new User(Integer.parseInt(id), email, firstName, secondName, new SimpleDateFormat(numberFormatPattern).parse(birthDate), Double.parseDouble(rating), Integer.parseInt(tripAmount), phoneNumber, new Role(role));
        }
        return user;
    }

    private String setDataToPage(HttpServletRequest req, String role, String listLabel, boolean isAdmin) throws ServiceException {
        List<User> userList = new ReadUserService().find(role);
        if (userList != null){
            req.setAttribute(listLabel, userList);
        }
        return isAdmin ? role.equalsIgnoreCase(UserRoleType.DRIVER) ? JspPagePath.DRIVER_ALL_INFO_PAGE : JspPagePath.PASSENGER_ALL_INFO_PAGE
                : role.equalsIgnoreCase(UserRoleType.DRIVER) ? JspPagePath.DRIVER_HOME_PAGE : JspPagePath.PASSENGER_HOME_PAGE;
    }
}
