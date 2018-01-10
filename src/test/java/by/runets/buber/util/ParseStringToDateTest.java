package by.runets.buber.util;

import by.runets.buber.infrastructure.util.DateLocalePattern;
import by.runets.buber.infrastructure.util.ParseStringToDate;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class ParseStringToDateTest {
    @Test
    public void testParse() throws ParseException {
       /* String date = "15.12.1997";
        String pattern = "dd.MM.yyyy";
        Date expected = new ParseStringToDate().parse(date, pattern, new Locale("ru", "RU"));*/
        Date d = DateLocalePattern.EN.getDate("12/15/1997");
        System.out.println(d);
    }
}
