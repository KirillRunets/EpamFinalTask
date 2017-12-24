package validation;

import exception.AuthenticationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static validation.constant.ValidationConstant.EMAIL_PATTERN;
import static validation.constant.ValidationConstant.PASSWORD_PATTERN;

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

    public boolean isLogInData(String email, String password) throws AuthenticationException {
        if (isStringEqualsPattern(email, EMAIL_PATTERN) || isStringEqualsPattern(password, PASSWORD_PATTERN)){
            return false;
        }
        return true;
    }
}
