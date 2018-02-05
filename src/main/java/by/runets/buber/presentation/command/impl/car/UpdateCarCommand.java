package by.runets.buber.presentation.command.impl.car;

import by.runets.buber.application.service.car.EditCarService;
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
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * This class provides method to implement update car command from admin or driver.
 */
public class UpdateCarCommand extends CarCommand implements Command{
    private final static Logger LOGGER = LogManager.getLogger(UpdateCarCommand.class);
    private EditCarService editCarService;

    public UpdateCarCommand(EditCarService editCarService) {
        this.editCarService = editCarService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();

        String locale = req.getSession().getAttribute(RequestParameter.LOCALE) == null ? RequestParameter.DEFAULT_LOCALE : req.getSession().getAttribute(RequestParameter.LOCALE).toString();
        User sessionUser = (User) req.getSession(false).getAttribute(UserRoleType.USER);
        boolean isAdmin = sessionUser.getRole().getRoleName().equalsIgnoreCase(UserRoleType.ADMIN);

        try {
            Car car = isAdmin ? initByAdmin(req, sessionUser, locale) : initByDriver(req, sessionUser, locale);
            router = car != null
                    ? editCarService.edit(car)
                    ? rightRoute(req, car, isAdmin) : errorRoute(req, locale) : errorRoute(req, locale);
        } catch (ServiceException | ParseException e) {
            LOGGER.error(e);
        }

        return router;
    }

    private Car initByAdmin(HttpServletRequest req, User user, String locale) throws ParseException {
        Car car = null;

        String id = req.getParameter(RequestParameter.CAR_ID);
        String releaseDate = req.getParameter(RequestParameter.RELEASE_DATE);
        String mark = req.getParameter(RequestParameter.MARK);
        String model = req.getParameter(RequestParameter.MODEL);
        String numberFormatPattern = NumberFormatLocaleFactory.factory(locale);

        if (RequestValidator.getInstance().isValidate(id) && RequestValidator.getInstance().isValidate(mark) && RequestValidator.getInstance().isValidate(model) && RequestValidator.getInstance().isValidate(releaseDate)){
            car = new Car(new Integer(id), mark, model, new SimpleDateFormat(numberFormatPattern).parse(releaseDate), user);
        }
        return car;
    }

    private Car initByDriver(HttpServletRequest req, User user, String locale) throws ParseException {
        Car car = null;

        String id = req.getParameter(RequestParameter.CAR_ID);
        String mark = req.getParameter(RequestParameter.MARK);
        String model = req.getParameter(RequestParameter.MODEL);

        String releaseDate = req.getParameter(RequestParameter.RELEASE_DATE);
        String numberFormatPattern = NumberFormatLocaleFactory.factory(locale);
        String licensePlate = req.getParameter(RequestParameter.LICENSE_PLATE);

        if (RequestValidator.getInstance().isValidate(mark) && RequestValidator.getInstance().isValidate(model) && RequestValidator.getInstance().isValidate(releaseDate) && RequestValidator.getInstance().isValidate(licensePlate)){
            car = new Car(new Integer(id), mark, model, new SimpleDateFormat(numberFormatPattern).parse(releaseDate), user, licensePlate);
        }

        return car;
    }

    private Router rightRoute(HttpServletRequest req, Car car, boolean isAdmin){
        Router router = new Router();
        String page = null;

        updateCarListInSession(req, LabelParameter.ADMIN_CAR_LIST, car);
        page = isAdmin ? JspPagePath.ADMIN_CAR_LIST_PAGE : JspPagePath.DRIVER_CAR_PROFILE_PAGE;

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.REDIRECT);

        return router;
    }

    private Router errorRoute(HttpServletRequest req, String locale) {
        Router router = new Router();

        req.setAttribute(LabelParameter.ERROR_LABEL, LocaleFileManager.getLocale(locale).getProperty(PropertyKey.ERROR_LABEL_MESSAGE));

        router.setPagePath(JspPagePath.CAR_FORM_PAGE);
        router.setRouteType(Router.RouteType.FORWARD);

        return router;
    }

}
