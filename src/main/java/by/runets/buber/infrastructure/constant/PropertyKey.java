package by.runets.buber.infrastructure.constant;

public class PropertyKey {
    private PropertyKey() {
    }

    //keys to config.properties file which provides database data
    public static final String DB_URL = "db.url";
    public static final String DB_USER = "db.user";
    public static final String DB_PASSWORD = "db.password";
    public static final String DB_POOL_SIZE = "db.poolSize";


    //keys to config.properties file which provides pages data
    public final static String LOGIN_ERROR_LABEL_MESSAGE = "label.loginError";
    public final static String SIGN_UP_ERROR_LABEL_MESSAGE = "label.signUpError";

}
