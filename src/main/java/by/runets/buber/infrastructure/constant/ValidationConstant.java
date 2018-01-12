package by.runets.buber.infrastructure.constant;

public class ValidationConstant {
    public final static String EMAIL_PATTERN = "[A-Za-z\\d]+[.]?[a-z0-9]+[@]{1}[a-z]+[(.)][a-z]+";
    public final static String PASSWORD_PATTERN = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}";
    public final static String NAME_PATTERN = "([A-Z][a-z]+)|([А-ЯІЎЁ][а-яіўё]+)";
    public final static String INTEGER_NUMBER_PATTERN = "[\\d]+";
    public final static String FLOAT_NUMBER_PATTERN = "([\\d]+[.]?[\\d]+)";
    public final static String PHONE_NUMBER_PATTERN = "([^-][^A-Za-z.]+)";
    public final static String USER_ROLE_PATTERN = "(DRIVER)|(PASSENGER)|(ADMIN)";
}
