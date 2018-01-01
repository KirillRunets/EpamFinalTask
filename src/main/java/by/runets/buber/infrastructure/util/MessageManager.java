package by.runets.buber.infrastructure.util;

import java.util.Locale;
import java.util.ResourceBundle;

public enum MessageManager {
    EN(ResourceBundle.getBundle("message.properties", new Locale("en", "US"))),
    BE(ResourceBundle.getBundle("message.properties", new Locale("be", "BY"))),
    RU(ResourceBundle.getBundle("message.properties", new Locale("ru", "RU")));

    private ResourceBundle resourceBundle;

    MessageManager(ResourceBundle resourceBundle){
        this.resourceBundle = resourceBundle;
    }

    public String getMessage(String key){
        return resourceBundle.getString(key);
    }
}
