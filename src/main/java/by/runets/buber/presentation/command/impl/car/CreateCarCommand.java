package by.runets.buber.presentation.command.impl.car;

import by.runets.buber.application.service.car.CreateCarService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.Car;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.*;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.infrastructure.util.NumberFormatLocaleFactory;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * This class provides method to implement create car command from admin or driver role type.
 */
public class CreateCarCommand extends CarCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(CreateCarCommand.class);
    private CreateCarService createCarService;

    public CreateCarCommand(CreateCarService createCarService) {
        this.createCarService = createCarService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        String page = null;

        User sessionUser = (User) req.getSession(false).getAttribute(UserRoleType.USER);
        String locale = req.getSession().getAttribute(RequestParameter.LOCALE) == null ? RequestParameter.DEFAULT_LOCALE : req.getSession().getAttribute(RequestParameter.LOCALE).toString();
        boolean isAdmin = sessionUser.getRole().getRoleName().equalsIgnoreCase(UserRoleType.ADMIN);
        Car car = null;
        try {
            if (isAdmin){
                car = initByAdmin(req, sessionUser, locale);
                updateCarListInSession(req, LabelParameter.ADMIN_CAR_LIST, car);
                page = JspPagePath.ADMIN_HOME_PAGE;
            } else {
                car = initByDriver(req, sessionUser, locale);
                setCarToUserSession(req, car);
                page = JspPagePath.DRIVER_CAR_PROFILE_PAGE;
            }
            createCarService.create(car);
        } catch (ParseException | ServiceException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.REDIRECT);

        return router;
    }

    /**
     * Call if session user role type is admin. Initialize car object if request values are valid else return null.
     * @param req
     * @param user
     * @param locale
     * @return user object or null.
     * @throws ParseException
     */
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

    /**
     * Call if session user role type is driver. Initialize car object if request values are valid else return null.
     * @param req
     * @param user
     * @param locale
     * @return car object or null.
     * @throws ParseException
     */
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
}
