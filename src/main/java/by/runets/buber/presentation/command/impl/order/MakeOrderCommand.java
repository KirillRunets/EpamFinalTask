package by.runets.buber.presentation.command.impl.order;

import by.runets.buber.application.service.order.MakeOrderService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.Order;
import by.runets.buber.domain.entity.Point;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.*;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.infrastructure.util.LocaleFileManager;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.Driver;
import java.util.Date;
import java.util.Optional;

public class MakeOrderCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(MakeOrderCommand.class);
    private MakeOrderService makeOrderService;

    public MakeOrderCommand(MakeOrderService makeOrderService) {
        this.makeOrderService = makeOrderService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        String page = null;
        String locale = req.getSession(false).getAttribute(RequestParameter.LOCALE) == null ? RequestParameter.DEFAULT_LOCALE : req.getSession().getAttribute(RequestParameter.LOCALE).toString();

        Order order = init(req);
        try {
            if (order != null && makeOrderService.makeOrder(order)){
                page = JspPagePath.FREE_DRIVERS_FOR_PASSENGER_PAGE;
            } else {
                page = JspPagePath.FREE_DRIVERS_FOR_PASSENGER_PAGE + "?" + LabelParameter.ERROR_LABEL + "=" + LocaleFileManager.getLocale(locale).getProperty(PropertyKey.ORDER_ERROR);
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        router.setRouteType(Router.RouteType.REDIRECT);
        router.setPagePath(page);

        return router;
    }

    private Order init(HttpServletRequest req){
        User sessionUser = (User) req.getSession().getAttribute(UserRoleType.USER);
        Order order = new Order();

        Double distance = (Double) req.getSession().getAttribute(LabelParameter.TRIP_DISTANCE_LABEL);
        Double time = (Double) req.getSession().getAttribute(LabelParameter.TRIP_TIME_LABEL);
        Double cost = (Double) req.getSession().getAttribute(LabelParameter.TRIP_COST_LABEL);
        Point destinationPoint = (Point) req.getSession().getAttribute(LabelParameter.DESTINATION_POINT);
        String driverId = req.getParameter(RequestParameter.DRIVER_ID);

        if (RequestValidator.getInstance().isValidateOrderData(driverId)){
            order = new Order();
            order.setPassenger(new User(sessionUser.getId()));
            order.setDriver(new User(Integer.parseInt(driverId)));
            order.setDistance(distance);
            order.setTripTime(time);
            order.setTripCost(cost);
            order.setOrderDate(new Date());
            order.setStartPoint(sessionUser.getCurrentLocation());
            order.setDestinationPoint(destinationPoint);
            order.setPaid(false);
        }

        return order;
    }
}
