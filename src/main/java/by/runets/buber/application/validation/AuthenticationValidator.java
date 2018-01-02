package by.runets.buber.application.validation;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthenticationValidator {
    private static AuthenticationValidator instance;

    private AuthenticationValidator() {
    }

    public static AuthenticationValidator getInstance(){
        if (instance == null){
            instance = new AuthenticationValidator();
        }
        return instance;
    }

    private boolean isStringEqualsPattern(String data, String regex){
        if (data == null || data.isEmpty()){
            return false;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(data);
        return m.matches();
    }

    public boolean isValidateLogInData(String email, String password) {
        return email != null && !email.isEmpty() && password != null && !password.isEmpty();
    }

    public boolean isValidateRegisterData(String email, String password, String firstName, String secondName, String role){
        return email != null && !email.isEmpty() && password != null && !password.isEmpty() && firstName != null && !firstName.isEmpty()&& secondName != null && !secondName.isEmpty()&& role != null && !role.isEmpty();
    }
}
