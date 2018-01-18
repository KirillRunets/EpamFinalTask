package by.runets.buber.presentation.command.impl.car;

import by.runets.buber.application.service.car.EditCarService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.Car;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.LabelParameter;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.infrastructure.util.NumberFormatLocaleFactory;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class EditCarCommand extends CarCommand implements Command{
    private final static Logger LOGGER = LogManager.getLogger(EditCarCommand.class);
    private EditCarService editCarService;

    public EditCarCommand(EditCarService editCarService) {
        this.editCarService = editCarService;
    }

    @Override
    public Router execute(HttpServletRequest req) {
        Router router = new Router();
        String page = null;

        User sessionUser = (User) req.getSession(false).getAttribute(UserRoleType.USER);
        try {
            Car car = init(req);
            if (editCarService.edit(car)){
                updateCarListInSession(req, LabelParameter.ADMIN_CAR_LIST, car);
                page = sessionUser.getRole().getRoleName().equalsIgnoreCase(UserRoleType.ADMIN) ? JspPagePath.ADMIN_CAR_LIST_PAGE : JspPagePath.DRIVER_CAR_PROFILE_PAGE;
            } else {
                page = JspPagePath.CAR_FORM_PAGE;
            }
        } catch (ServiceException | ParseException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.REDIRECT);

        return router;
    }

    public Car init(HttpServletRequest req) throws ParseException {
        Car car = null;

        User user = (User) req.getSession(false).getAttribute(UserRoleType.USER);
        String id = req.getParameter(RequestParameter.CAR_ID);
        String releaseDate = req.getParameter(RequestParameter.RELEASE_DATE);
        String mark = req.getParameter(RequestParameter.MARK);
        String model = req.getParameter(RequestParameter.MODEL);
        String locale = req.getSession().getAttribute(RequestParameter.LOCALE) == null ? RequestParameter.DEFAULT_LOCALE : req.getSession().getAttribute(RequestParameter.LOCALE).toString();
        String numberFormatPattern = NumberFormatLocaleFactory.factory(locale);

        if (RequestValidator.getInstance().isValidate(id) && RequestValidator.getInstance().isValidate(mark) && RequestValidator.getInstance().isValidate(model) && RequestValidator.getInstance().isValidate(releaseDate)){
            car = new Car(new Integer(id), mark, model, new SimpleDateFormat(numberFormatPattern).parse(releaseDate), user);
        }
        return car;
    }

}
