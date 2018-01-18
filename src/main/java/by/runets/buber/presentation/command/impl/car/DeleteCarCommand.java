package by.runets.buber.presentation.command.impl.car;

import by.runets.buber.application.service.car.DeleteCarService;
import by.runets.buber.application.service.car.ReadCarService;
import by.runets.buber.application.service.user.ReadUserService;
import by.runets.buber.application.validation.RequestValidator;
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
import java.text.ParseException;
import java.util.List;

public class DeleteCarCommand extends CarCommand implements Command{
    private final static Logger LOGGER = LogManager.getLogger(DeleteCarCommand.class);
    private DeleteCarService deleteCarService;

    public DeleteCarCommand(DeleteCarService deleteCarService) {
        this.deleteCarService = deleteCarService;
    }

    @Override
    public Router execute(HttpServletRequest req) {
        Router router = new Router();
        String page = null;

        User sessionUser = (User) req.getSession(false).getAttribute(UserRoleType.USER);
        List<Car> sessionCarList = (List<Car>) req.getSession().getAttribute(LabelParameter.ADMIN_CAR_LIST);

        try {
            Car car = init(req);
            deleteCarService.delete(car);
            deleteCarInCarListSession(req, LabelParameter.ADMIN_CAR_LIST, car.getId());
            page = sessionUser.getRole().getRoleName().equalsIgnoreCase(UserRoleType.ADMIN) ? JspPagePath.ADMIN_CAR_LIST_PAGE : JspPagePath.DRIVER_CAR_PROFILE_PAGE;
        } catch (ServiceException | ParseException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.REDIRECT);

        return router;
    }
}
