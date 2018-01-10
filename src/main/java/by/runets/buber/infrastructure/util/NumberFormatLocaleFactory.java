package by.runets.buber.infrastructure.util;

public class NumberFormatLocaleFactory {
    public static String factory(String locale){
        String pattern = null;
        switch (locale){
            case "en_US":
                pattern = "MM/dd/yyyy";
                break;
            case "be_BY":
            case "ru_RU":
                pattern = "yyyy-MM-dd";
                break;
        }
        return pattern;
    }
}
