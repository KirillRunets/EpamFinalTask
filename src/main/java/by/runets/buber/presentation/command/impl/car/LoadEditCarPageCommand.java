package by.runets.buber.presentation.command.impl.car;

import by.runets.buber.application.service.car.ReadCarService;
import by.runets.buber.domain.entity.Car;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.LabelParameter;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

/**
 * This class provides method to implement load car data to jsp form command.
 */
public class LoadEditCarPageCommand extends CarCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(LoadEditCarPageCommand.class);
    private ReadCarService readCarService;

    public LoadEditCarPageCommand(ReadCarService readCarService) {
        this.readCarService = readCarService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();

        try {
            Car car = init(req);
            car = readCarService.findCarById(car.getId());
            req.getSession().setAttribute(LabelParameter.CAR, car);
        } catch (ParseException | ServiceException e) {
            LOGGER.error(e);
        }

        router.setPagePath(JspPagePath.CAR_FORM_PAGE);
        router.setRouteType(Router.RouteType.REDIRECT);

        return router;
    }
}
