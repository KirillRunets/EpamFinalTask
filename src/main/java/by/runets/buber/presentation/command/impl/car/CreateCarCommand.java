package by.runets.buber.presentation.command.impl.car;

import by.runets.buber.application.service.car.CreateCarService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.Car;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.JspPagePath;
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

public class CreateCarCommand extends CarCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(CreateCarCommand.class);
    private CreateCarService createCarService;

    public CreateCarCommand(CreateCarService createCarService) {
        this.createCarService = createCarService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        try {
            User user = (User) req.getSession(false).getAttribute(UserRoleType.USER);

            Car car = init(req);
            if (car != null){
                createCarService.create(car);
                page = user.getRole().getRoleName().equalsIgnoreCase(UserRoleType.ADMIN) ? JspPagePath.ADMIN_HOME_PAGE : JspPagePath.DRIVER_CAR_PROFILE_PAGE;
            }
        } catch (ParseException | ServiceException e) {
            LOGGER.error(e);
        }

        return page;
    }

    public Car init(HttpServletRequest req) throws ParseException {
        Car car = null;

        User user = (User) req.getSession(false).getAttribute(UserRoleType.USER);
        String mark = req.getParameter(RequestParameter.MARK);
        String model = req.getParameter(RequestParameter.MODEL);
        String releaseDate = req.getParameter(RequestParameter.RELEASE_DATE);
        String licensePlate = null;
        String locale = req.getSession().getAttribute(RequestParameter.LOCALE) == null ? RequestParameter.DEFAULT_LOCALE : req.getSession().getAttribute(RequestParameter.LOCALE).toString();
        String numberFormatPattern = NumberFormatLocaleFactory.factory(locale);

        if (user.getRole().getRoleName().equalsIgnoreCase(UserRoleType.DRIVER)){
            licensePlate = req.getParameter(RequestParameter.LICENSE_PLATE);
        }

        if (RequestValidator.getInstance().isValidate(mark) && RequestValidator.getInstance().isValidate(model) && RequestValidator.getInstance().isValidate(releaseDate)){
            car = new Car(mark, model, new SimpleDateFormat(numberFormatPattern).parse(releaseDate), user, licensePlate);
        }

        return car;
    }
}
