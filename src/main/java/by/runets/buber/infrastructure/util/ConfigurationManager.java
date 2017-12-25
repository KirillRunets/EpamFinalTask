package infrastructure.util;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private final static String CONFIG_PATH = "property/config/config.properties";
    private ResourceBundle resourceBundle = ResourceBundle.getBundle(CONFIG_PATH);

    private ConfigurationManager(){}

    public String getBundle(String key){
        return resourceBundle.getString(key);
    }
}
