package by.runets.buber.presentation.command.impl.order;


import by.runets.buber.application.service.order.CalculateOrderDataService;
import by.runets.buber.domain.entity.Point;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculateOrderDataCommand implements Command{
    private final static Logger LOGGER = LogManager.getLogger(CalculateOrderDataCommand.class);
    private CalculateOrderDataService calculateOrderDistanceService;

    public CalculateOrderDataCommand(CalculateOrderDataService calculateOrderDistanceService) {
        this.calculateOrderDistanceService = calculateOrderDistanceService;
    }


    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();

        Double latitude = Double.valueOf(req.getParameter(RequestParameter.LATITUDE));
        Double longitude = Double.valueOf(req.getParameter(RequestParameter.LONGITUDE));

        Double distance = calculateOrderDistanceService.calculateDistance(new Point(latitude, longitude));
        Double time = calculateOrderDistanceService.calculateTime(distance);
        Double cost = calculateOrderDistanceService.calculateCost(distance, time);



        return null;
    }
}
