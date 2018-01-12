package by.runets.buber.application.validation;


import by.runets.buber.infrastructure.constant.ValidationConstant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestValidator {
    private static RequestValidator instance;

    private RequestValidator() {
    }

    public static RequestValidator getInstance(){
        if (instance == null){
            instance = new RequestValidator();
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
        return isStringEqualsPattern(email, ValidationConstant.EMAIL_PATTERN)
                && isStringEqualsPattern(password, ValidationConstant.PASSWORD_PATTERN);
    }

    public boolean isValidateRegisterData(String email, String password, String firstName, String secondName, String role){
        return isStringEqualsPattern(email, ValidationConstant.EMAIL_PATTERN)
                && isStringEqualsPattern(password, ValidationConstant.PASSWORD_PATTERN)
                && isStringEqualsPattern(firstName, ValidationConstant.NAME_PATTERN)
                && isStringEqualsPattern(secondName, ValidationConstant.NAME_PATTERN)
                && isStringEqualsPattern(role, ValidationConstant.USER_ROLE_PATTERN);
    }

    public boolean isValidateDriverData(String id, String email, String firstName, String secondName, String birthDate, String phoneNumber, String rating, String tripAmount, String role){
        return isStringEqualsPattern(id, ValidationConstant.INTEGER_NUMBER_PATTERN)
                && isStringEqualsPattern(email, ValidationConstant.EMAIL_PATTERN)
                && birthDate != null && !birthDate.isEmpty()
                && isStringEqualsPattern(firstName, ValidationConstant.NAME_PATTERN)
                && isStringEqualsPattern(secondName, ValidationConstant.NAME_PATTERN)
                && isStringEqualsPattern(phoneNumber, ValidationConstant.PHONE_NUMBER_PATTERN)
                && isStringEqualsPattern(rating, ValidationConstant.FLOAT_NUMBER_PATTERN)
                && isStringEqualsPattern(tripAmount, ValidationConstant.INTEGER_NUMBER_PATTERN)
                && isStringEqualsPattern(role, ValidationConstant.USER_ROLE_PATTERN);
    }

    public boolean isValidate(String string){
        return string != null && !string.isEmpty();
    }

    public boolean isValidateBanData(String id, String date, String banId){
        return  isStringEqualsPattern(id, ValidationConstant.INTEGER_NUMBER_PATTERN)
                && date != null && !date.isEmpty()
                && isStringEqualsPattern(banId, ValidationConstant.INTEGER_NUMBER_PATTERN);
    }
}
