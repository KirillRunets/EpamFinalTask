package by.runets.buber.presentation.command.impl.user;

import by.runets.buber.application.service.user.ReadUserService;
import by.runets.buber.application.service.user.UpdateUserService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.Role;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.*;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.infrastructure.util.LocaleFileManager;
import by.runets.buber.infrastructure.util.NumberFormatLocaleFactory;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UpdateUserCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(UpdateUserCommand.class);
    private UpdateUserService updateUserService;

    public UpdateUserCommand(UpdateUserService updateUserService) {
        this.updateUserService = updateUserService;
    }

    @Override
    public Router execute(HttpServletRequest req) {
        Router router = new Router();
        String page = null;
        boolean isAdmin = false;
        try {
            User sessionUser = (User) req.getSession(false).getAttribute(UserRoleType.USER);
            User user = init(req, sessionUser);
            if (user != null){
                isAdmin = sessionUser.getRole().getRoleName().equalsIgnoreCase(UserRoleType.ADMIN);
                updateUserService.update(user);
            } else {
                req.setAttribute("", "");
            }
            page = isAdmin ? switchPageByAdmin(user, req) : switchPageByUser(user, req);
        } catch (ServiceException | ParseException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.REDIRECT);

        return router;
    }

    private String switchPageByAdmin(User user, HttpServletRequest req) throws ServiceException {
        String page = null;
        switch (user.getRole().getRoleName()){
            case UserRoleType.DRIVER:
                updateUserInSession(req, user, LabelParameter.DRIVER_LIST_LABEL);
                page = JspPagePath.DRIVER_ALL_INFO_PAGE;
                break;
            case UserRoleType.PASSENGER:
                updateUserInSession(req, user, LabelParameter.PASSENGER_LIST_LABEL);
                page = JspPagePath.PASSENGER_ALL_INFO_PAGE;
                break;
        }

        return page;
    }

    private String switchPageByUser(User user, HttpServletRequest req) throws ServiceException {
        String page = null;
        req.getSession(false).setAttribute(UserRoleType.USER, user);
        page = user.getRole().getRoleName().equalsIgnoreCase(UserRoleType.DRIVER) ? JspPagePath.DRIVER_PROFILE_PAGE : JspPagePath.PASSENGER_PROFILE_PAGE;
        return page;
    }

    private User init(HttpServletRequest req, User sessionUser) throws ParseException {
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

        if (RequestValidator.getInstance().isValidateDriverData(id, email, firstName, secondName, birthDate, phoneNumber, rating, tripAmount, role)) {
            switch (sessionUser.getRole().getRoleName().toUpperCase()){
                case UserRoleType.ADMIN:
                    user = new User(Integer.parseInt(id), email, firstName, secondName, new SimpleDateFormat(NumberFormatLocaleFactory.factory(locale)).parse(birthDate), Double.parseDouble(rating), Integer.parseInt(tripAmount), phoneNumber, new Role(role));
                    break;
                case UserRoleType.DRIVER:
                case UserRoleType.PASSENGER:
                    user = sessionUser;
                    user.setEmail(email);
                    user.setFirstName(firstName);
                    user.setSecondName(secondName);
                    user.setBirthDate(new SimpleDateFormat(NumberFormatLocaleFactory.factory(locale)).parse(birthDate));
                    user.setRating(Double.parseDouble(rating));
                    user.setTripAmount(Integer.parseInt(tripAmount));
                    user.setPhoneNumber(phoneNumber);
                    user.setRole(new Role(role));
                    break;
            }
        }

        return user;
    }

    private void updateUserInSession(HttpServletRequest req, User user, String label){
        HttpSession httpSession = req.getSession();
        List<User> userList = (List<User>) httpSession.getAttribute(label);
        userList = updateUserInSessionList(userList, user);
        httpSession.removeAttribute(label);
        httpSession.setAttribute(label, userList);
    }

    private List<User> updateUserInSessionList(List<User> sessionUserList, User newUser){
        return sessionUserList.stream()
                .map(user1 -> {
                    if (user1.getId() == newUser.getId()){
                        return newUser;
                    } else {
                        return user1;
                    }
                })
                .collect(Collectors.toList());
    }

}
