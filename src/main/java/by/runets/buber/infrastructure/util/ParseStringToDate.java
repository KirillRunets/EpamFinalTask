package by.runets.buber.infrastructure.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ParseStringToDate {

    public Date parse(String dateString, String pattern, Locale locale) throws ParseException {
        Date date = null;
        DateFormat df = new SimpleDateFormat(pattern, locale);

        date = df.parse(dateString);

        return date;
    }
}
