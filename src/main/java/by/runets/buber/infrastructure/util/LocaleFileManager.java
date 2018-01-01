package by.runets.buber.infrastructure.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleFileManager {
    private ResourceBundle resourceBundle;

    public LocaleFileManager(String file, Locale locale) {
        resourceBundle = ResourceBundle.getBundle(file, locale);
    }

    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
