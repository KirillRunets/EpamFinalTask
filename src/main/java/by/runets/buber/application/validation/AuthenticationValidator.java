package application.validation;

import infrastructure.exception.AuthenticationException;

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

    public boolean isValidateLogInData(String email, String password) throws AuthenticationException {
        return email != null && !email.isEmpty() || password != null && !password.isEmpty();
    }
}
