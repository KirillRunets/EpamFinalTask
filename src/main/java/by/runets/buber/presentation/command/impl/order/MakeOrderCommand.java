package by.runets.buber.presentation.command.impl.order;

import by.runets.buber.application.service.order.MakeOrderService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.Order;
import by.runets.buber.domain.entity.Point;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.LabelParameter;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        Order order = init(req);
        try {
            if (makeOrderService.makeOrder(order)){
                req.getSession().setAttribute(LabelParameter.ORDER, order);
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        router.setRouteType(Router.RouteType.REDIRECT);

        return null;
    }

    private Order init(HttpServletRequest req){
        User sessionUser = (User) req.getSession().getAttribute(UserRoleType.USER);
        Order order = new Order();

        String distance = (String) req.getSession().getAttribute(LabelParameter.TRIP_DISTANCE_LABEL);
        String time = (String) req.getSession().getAttribute(LabelParameter.TRIP_TIME_LABEL);
        String cost = (String) req.getSession().getAttribute(LabelParameter.TRIP_COST_LABEL);
        Point destinationPoint = (Point) req.getSession().getAttribute(LabelParameter.DESTINATION_POINT);
        String driverId = req.getParameter(RequestParameter.DRIVER_ID);


        if (RequestValidator.getInstance().isValidateOrderData(driverId, distance, time, cost)){
            order = new Order();
            order.setPassengerId(sessionUser.getId());
            order.setDriverId(Integer.parseInt(driverId));
            order.setDistance(Double.valueOf(distance));
            order.setTripTime(Double.valueOf(time));
            order.setTripCost(Double.valueOf(cost));
            order.setStartPoint(Optional.of(sessionUser.getCurrentLocation()));
            order.setDestinationPoint(Optional.of(destinationPoint));
        }

        return order;
    }
}
