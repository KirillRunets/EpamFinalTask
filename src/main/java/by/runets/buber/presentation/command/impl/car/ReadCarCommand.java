package by.runets.buber.presentation.command.impl.car;

import by.runets.buber.application.service.car.ReadCarService;
import by.runets.buber.domain.entity.Car;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.LabelParameter;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This class provides method to implement read car command from admin or driver role type.
 */
public class ReadCarCommand extends CarCommand implements Command{
    private final static Logger LOGGER = LogManager.getLogger(ReadCarCommand.class);
    private ReadCarService readCarService;

    public ReadCarCommand(ReadCarService readCarService) {
        this.readCarService = readCarService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        String page = null;
        User sessionUser = (User) req.getSession(false).getAttribute(UserRoleType.USER);

        try {
            List<Car> carList = readCarService.findCars();
            if (carList != null){
                setCarListToSession(req, LabelParameter.ADMIN_CAR_LIST, carList);
                page = sessionUser.getRole().getRoleName().equalsIgnoreCase(UserRoleType.ADMIN) ? JspPagePath.ADMIN_CAR_LIST_PAGE : JspPagePath.DRIVER_CAR_PROFILE_PAGE;
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.REDIRECT);

        return router;
    }
}
