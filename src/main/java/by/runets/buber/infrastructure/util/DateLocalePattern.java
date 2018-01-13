package by.runets.buber.infrastructure.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public enum DateLocalePattern {
    RU(new SimpleDateFormat("dd.MM.yyyy", new Locale("ru", "RU"))),
    BE(new SimpleDateFormat("dd.MM.yyyy", new Locale("be", "BY"))),
    EN(new SimpleDateFormat("MM/dd/yyyy", new Locale("en", "US")));

    private SimpleDateFormat dateFormat;

    DateLocalePattern(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Date getDate(String dateString) throws ParseException {
        return dateFormat.parse(dateString);
    }
}
