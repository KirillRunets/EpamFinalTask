package by.runets.buber.application.service.order;

import by.runets.buber.domain.entity.Point;
import by.runets.buber.domain.enumeration.TrafficEnum;
import by.runets.buber.infrastructure.constant.OrderConstant;
import by.runets.buber.infrastructure.util.RandomGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * This class provides method to calculate order data.
 */
public class CalculateOrderDataService {
    private final static Logger LOGGER = LogManager.getLogger(CalculateOrderDataService.class);
    public Double calculateDistance(Point departurePoint, Point destinationPoint){
        Double distance = Math.hypot((destinationPoint.getX() - departurePoint.getX()), (destinationPoint.getY() - departurePoint.getY()));
        return Math.abs(distance);
    }

    public Double calculateTime(Double distance, Integer averageSpeed){
        return distance / averageSpeed * 60;
    }

    public BigDecimal calculateCost(Double distance, Long time){
        return BigDecimal.valueOf(OrderConstant.CYLOMETER_PRICE * distance + OrderConstant.MINUTE_PRICE * time / 60);
    }
}
