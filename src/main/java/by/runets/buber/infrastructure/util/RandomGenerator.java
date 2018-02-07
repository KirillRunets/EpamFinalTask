package by.runets.buber.infrastructure.util;

import by.runets.buber.domain.entity.Point;
import by.runets.buber.domain.enumeration.TrafficEnum;
import by.runets.buber.infrastructure.constant.OrderConstant;

import java.util.Random;

public class RandomGenerator {


    public static Point generatePoint(){
        Random randomGenerator = new Random();
        Double latitude = (double) randomGenerator.nextInt(100);
        Double longitude = (double) randomGenerator.nextInt(100);
        return new Point(latitude, longitude);
    }

    public static int generateAverageSpeed(TrafficEnum trafficEnum){
        Random randomGenerator = new Random();
        return (int) (trafficEnum == TrafficEnum.CITY
                ? OrderConstant.CITY_AVERAGE_SPEED
                : OrderConstant.HIGHWAY_AVERAGE_SPEED);
    }
}
