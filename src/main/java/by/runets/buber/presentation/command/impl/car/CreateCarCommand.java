package by.runets.buber.presentation.command.impl.car;

import by.runets.buber.application.service.car.CreateCarService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.Car;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CreateCarCommand extends CarCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(CreateCarCommand.class);
    private CreateCarService createCarService;

    public CreateCarCommand(CreateCarService createCarService) {
        this.createCarService = createCarService;
    }

    @Override
    public Router execute(HttpServletRequest req) {
        Router router = new Router();
        String page = null;
        String sessionUser = null;
        try {
            String locale = req.getSession().getAttribute(RequestParameter.LOCALE) == null ? RequestParameter.DEFAULT_LOCALE : req.getSession().getAttribute(RequestParameter.LOCALE).toString();
            User user = (User) req.getSession(false).getAttribute(UserRoleType.USER);
            sessionUser = user.getRole().getRoleName();
            Car car = sessionUser.equalsIgnoreCase(UserRoleType.ADMIN) ? initByAdmin(req, user, locale) : initByDriver(req, user, locale);
            if (car != null){
                if (createCarService.create(car)) {
                    setUpdateCarInUserSession(req, user, car);
                } else {
                    req.setAttribute(LabelParameter.ERROR_LABEL, LocaleFileManager.getLocale(locale).getProperty(PropertyKey.CAR_LICENSE_EXIST_ERROR));
                }
            } else {
                req.setAttribute(LabelParameter.ERROR_LABEL, LocaleFileManager.getLocale(locale).getProperty(PropertyKey.ADD_CAR_ERROR));
            }
            page = sessionUser.equalsIgnoreCase(UserRoleType.ADMIN) ? JspPagePath.ADMIN_HOME_PAGE : JspPagePath.DRIVER_CAR_PROFILE_PAGE;
        } catch (ParseException | ServiceException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.REDIRECT);

        return router;
    }

    private Car initByAdmin(HttpServletRequest req, User user, String locale) throws ParseException {
        Car car = null;

        String mark = req.getParameter(RequestParameter.MARK);
        String model = req.getParameter(RequestParameter.MODEL);
        String releaseDate = req.getParameter(RequestParameter.RELEASE_DATE);
        String numberFormatPattern = NumberFormatLocaleFactory.factory(locale);

        if (RequestValidator.getInstance().isValidate(mark) && RequestValidator.getInstance().isValidate(model) && RequestValidator.getInstance().isValidate(releaseDate)){
            car = new Car(mark, model, new SimpleDateFormat(numberFormatPattern).parse(releaseDate), user);
        }

        return car;
    }

    private Car initByDriver(HttpServletRequest req, User user, String locale) throws ParseException {
        Car car = null;
        String markModel = req.getParameter(RequestParameter.MARK_MODEL);

        String[] parsedMarkModel = markModel.split(ValidationConstant.DELIMITER);
        String mark = parsedMarkModel[0];
        String model = parsedMarkModel[1];

        String releaseDate = req.getParameter(RequestParameter.RELEASE_DATE);
        String numberFormatPattern = NumberFormatLocaleFactory.factory(locale);
        String licensePlate = req.getParameter(RequestParameter.LICENSE_PLATE);

        if (RequestValidator.getInstance().isValidate(mark) && RequestValidator.getInstance().isValidate(model) && RequestValidator.getInstance().isValidate(releaseDate) && RequestValidator.getInstance().isValidate(licensePlate)){
            car = new Car(mark, model, new SimpleDateFormat(numberFormatPattern).parse(releaseDate), user, licensePlate);
        }

        return car;
    }

    private void setUpdateCarInUserSession(HttpServletRequest req, User user, Car car){
        if (user.getRole().getRoleName().equalsIgnoreCase(UserRoleType.DRIVER)){
            user.setCar(car);
            req.getSession(false).removeAttribute(UserRoleType.USER);
            req.getSession().setAttribute(UserRoleType.USER, user);
        }
    }
}
