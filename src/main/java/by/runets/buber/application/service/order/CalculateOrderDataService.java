package by.runets.buber.application.service.order;

import by.runets.buber.domain.entity.Point;
import by.runets.buber.domain.enumeration.TrafficEnum;
import by.runets.buber.infrastructure.constant.OrderConstant;
import by.runets.buber.infrastructure.util.RandomGenerator;

public class CalculateOrderDataService {
    public Double calculateDistance(Point departurePoint, Point destinationPoint){
        Double distance = Math.hypot((destinationPoint.getX() - departurePoint.getX()), (destinationPoint.getY() - departurePoint.getY()));
        return Math.abs(distance);
    }

    public Double calculateTime(Double distance, TrafficEnum trafficEnum){
        Double averageSpeed = RandomGenerator.generateAverageSpeed(trafficEnum);
        return  distance / averageSpeed * 60;
    }

    public Double calculateCost(Double distance, Double time){
        return OrderConstant.CYLOMETER_PRICE * distance + OrderConstant.MINUTE_PRICE * time;
    }
}
