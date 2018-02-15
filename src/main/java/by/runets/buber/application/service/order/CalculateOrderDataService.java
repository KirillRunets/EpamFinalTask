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
    public BigDecimal calculateCost(Double distance, Long time){
        Long minute = (time % 3600) / 60;
        return BigDecimal.valueOf(OrderConstant.CYLOMETER_PRICE * distance + OrderConstant.MINUTE_PRICE * minute);
    }
}
