package by.runets.buber.infrastructure.dao.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationParser {
    private final static String DELIMITER = " ";
    public static List<Double> parseLocation(String location){
        List<Double> coordinates = new ArrayList<>();
        Arrays.stream(location.split(DELIMITER)).forEach(i -> coordinates.add(Double.parseDouble(i)));
        return coordinates;
    }
}
