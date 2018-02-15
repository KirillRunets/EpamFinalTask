package by.runets.buber.infrastructure.parser;

import by.runets.buber.domain.entity.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationParser {
    private final static String DELIMITER = " ";
    public static List<Double> parseLocation(String location){
        List<Double> coordinates = new ArrayList<>();
        if (location != null && !location.isEmpty()){
            Arrays.stream(location.split(DELIMITER)).forEach(i -> coordinates.add(Double.parseDouble(i)));
        }
        return coordinates;
    }

    public static Point parseLocationToPoint(String location){
        List<Double> coordinates = new ArrayList<>();
        Point point = new Point();
        if (location != null && !location.isEmpty()){
            Arrays.stream(location.split(DELIMITER)).forEach(i -> coordinates.add(Double.parseDouble(i)));
        }
        point.setLatitude(coordinates.get(0));
        point.setLongitude(coordinates.get(coordinates.size() - 1));

        return point;
    }
}
