package by.runets.buber.presentation.command.impl.order;


import by.runets.buber.application.service.order.CalculateOrderDataService;
import by.runets.buber.application.service.order.CollectDriversToOrderService;
import by.runets.buber.domain.entity.Order;
import by.runets.buber.domain.entity.Point;
import by.runets.buber.domain.entity.User;
import by.runets.buber.domain.enumeration.TrafficEnum;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.LabelParameter;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.infrastructure.util.RandomGenerator;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Queue;

/**
 * This class provides method to calculate order data like trip cost, trip time, trip distance and other from passenger request.
 */
public class CalculateOrderDataCommand implements Command{
    private final static Logger LOGGER = LogManager.getLogger(CalculateOrderDataCommand.class);
    private CalculateOrderDataService calculateOrderDistanceService;
    private CollectDriversToOrderService collectDriversToOrderService;

    public CalculateOrderDataCommand(CalculateOrderDataService calculateOrderDistanceService, CollectDriversToOrderService collectDriversToOrderService) {
        this.calculateOrderDistanceService = calculateOrderDistanceService;
        this.collectDriversToOrderService = collectDriversToOrderService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();

        Double latitude = Double.valueOf(req.getParameter(RequestParameter.LATITUDE));
        Double longitude = Double.valueOf(req.getParameter(RequestParameter.LONGITUDE));
        TrafficEnum trafficEnum = TrafficEnum.valueOf(req.getParameter(RequestParameter.TRAFFIC).toUpperCase());
        User sessionPassenger = (User) req.getSession().getAttribute(UserRoleType.USER);

        Point departurePoint = RandomGenerator.generatePoint();
        Point destinationPoint = new Point(latitude, longitude);

        Integer averageSpeed = RandomGenerator.generateAverageSpeed(trafficEnum);
        Double distance = calculateOrderDistanceService.calculateDistance(departurePoint, destinationPoint);
        Long time = calculateOrderDistanceService.calculateTime(distance, averageSpeed).longValue();
        BigDecimal cost = calculateOrderDistanceService.calculateCost(distance, time);

        sessionPassenger.setCurrentLocation(departurePoint);

        try {
            Queue<User> priorityQueue = collectDriversToOrderService.collect(sessionPassenger);
            if (priorityQueue != null){
                req.getSession().setAttribute(LabelParameter.PRIORITY_DRIVERS_QUEUE_LABEL, priorityQueue);
                req.getSession().setAttribute(LabelParameter.TRIP_DISTANCE_LABEL, distance);
                req.getSession().setAttribute(LabelParameter.TRIP_TIME_LABEL, time);
                req.getSession().setAttribute(LabelParameter.TRIP_COST_LABEL, cost);
                req.getSession().setAttribute(LabelParameter.DESTINATION_POINT, destinationPoint);
                req.getSession().setAttribute(LabelParameter.AVERAGE_SPEED, averageSpeed);
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        router.setPagePath(JspPagePath.FREE_DRIVERS_FOR_PASSENGER_PAGE);
        router.setRouteType(Router.RouteType.REDIRECT);

        return router;
    }

}
