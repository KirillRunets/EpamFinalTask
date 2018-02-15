package by.runets.buber.presentation.command.impl.order;


import by.runets.buber.application.service.order.CalculateOrderDataService;
import by.runets.buber.application.service.order.CollectDriversToOrderService;
import by.runets.buber.domain.entity.Point;
import by.runets.buber.domain.entity.User;
import by.runets.buber.domain.enumeration.TrafficEnum;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.LabelParameter;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.infrastructure.parser.LocationParser;
import by.runets.buber.infrastructure.util.RandomGenerator;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Queue;

/**
 * This class provides method to calculate order data like trip cost, trip time, trip distance and other from passenger request.
 */
public class OrderDataCommand implements Command{
    private final static Logger LOGGER = LogManager.getLogger(OrderDataCommand.class);
    private CalculateOrderDataService calculateOrderDistanceService;
    private CollectDriversToOrderService collectDriversToOrderService;

    public OrderDataCommand(CalculateOrderDataService calculateOrderDistanceService, CollectDriversToOrderService collectDriversToOrderService) {
        this.calculateOrderDistanceService = calculateOrderDistanceService;
        this.collectDriversToOrderService = collectDriversToOrderService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();

        User sessionPassenger = (User) req.getSession().getAttribute(UserRoleType.USER);
        String duration = req.getParameter(RequestParameter.DURATION);
        String distance = req.getParameter(RequestParameter.DISTANCE);

        String departurePoint = req.getParameter(RequestParameter.DEPARTURE_POINT);
        String destinationPoint = req.getParameter(RequestParameter.DESTINATION_POINT);

        BigDecimal cost = calculateOrderDistanceService.calculateCost(distanceToKilometers(distance), new Long(duration));

        Point departure = LocationParser.parseLocationToPoint(departurePoint);
        Point destination = LocationParser.parseLocationToPoint(destinationPoint);

        sessionPassenger.setCurrentLocation(departure);

        try {
            Queue<User> priorityQueue = collectDriversToOrderService.collect(sessionPassenger);
            if (priorityQueue != null){
                req.getSession().setAttribute(LabelParameter.PRIORITY_DRIVERS_QUEUE_LABEL, priorityQueue);
                req.getSession().setAttribute(LabelParameter.TRIP_DISTANCE_LABEL, distanceToKilometers(distance));
                req.getSession().setAttribute(LabelParameter.TRIP_TIME_LABEL, timeToMinute(duration));
                req.getSession().setAttribute(LabelParameter.TRIP_COST_LABEL, cost);
                req.getSession().setAttribute(LabelParameter.DESTINATION_POINT, destination);
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        router.setPagePath(JspPagePath.FREE_DRIVERS_FOR_PASSENGER_PAGE);
        router.setRouteType(Router.RouteType.REDIRECT);

        return router;
    }

    private Double distanceToKilometers(String distance){
        return new Double(distance) / 1000;
    }

    private Long timeToMinute(String seconds){
        return (new Long(seconds) % 3600) / 60;
    }
}
