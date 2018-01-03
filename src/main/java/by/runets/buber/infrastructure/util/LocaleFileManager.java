package by.runets.buber.infrastructure.util;

import by.runets.buber.infrastructure.constant.PropertyPath;

import java.util.Locale;
import java.util.ResourceBundle;

public enum LocaleFileManager {
    RU(ResourceBundle.getBundle(PropertyPath.ERROR_PROPERTIES_FILE, new Locale("ru", "RU"))),
    EN(ResourceBundle.getBundle(PropertyPath.ERROR_PROPERTIES_FILE, new Locale("en", "US"))),
    BE(ResourceBundle.getBundle(PropertyPath.ERROR_PROPERTIES_FILE, new Locale("be", "BY")));
    private ResourceBundle resourceBundle;

    LocaleFileManager(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    public static LocaleFileManager getLocale(String locale){
        LocaleFileManager localeFileManager = null;
        switch (locale){
            case "be_BY":
                localeFileManager = BE;
                break;
            case "ru_RU":
                localeFileManager = RU;
                break;
            case "en_US":
                localeFileManager = EN;
                break;
        }
        return localeFileManager;
    }

    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
