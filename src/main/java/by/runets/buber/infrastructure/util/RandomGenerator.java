package by.runets.buber.infrastructure.util;

import by.runets.buber.domain.entity.Point;

import java.util.Random;

public class RandomGenerator {
    public static Point generateDeparturePoint(){
        Random randomGenerator = new Random();
        Double x = (double) randomGenerator.nextInt(1000);
        Double y = (double) randomGenerator.nextInt(1000);
        return new Point(x, y);
    }

    public static Double generateAverageSpeed(){
        Random randomGenerator = new Random();
        return (double) randomGenerator.nextInt(100);
    }
}
