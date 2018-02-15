package by.runets.buber.parser;

import by.runets.buber.infrastructure.parser.LocationParser;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class ParserTest {
    @Test
    public void testLocationParser(){
        String expected = "1.0 2.0";
        List<Double> actual = Arrays.asList(1.0, 2.0);
        Assert.assertEquals(actual, LocationParser.parseLocation(expected));
    }
}
