package by.runets.buber.infrastructure.connection;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DBManager {
    private final static DBManager instance = new DBManager();
    private ResourceBundle bundle = null;

    private DBManager() {

    }

    public void initialize() throws MissingResourceException {
        //bundle = ResourceBundle.getBundle(FilePath.DB_CONFIGURATION);
    }

    public static DBManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
