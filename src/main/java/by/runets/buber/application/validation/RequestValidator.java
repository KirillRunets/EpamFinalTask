package by.runets.buber.application.validation;


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
        return email != null && !email.isEmpty() && password != null && !password.isEmpty();
    }

    public boolean isValidateRegisterData(String email, String password, String firstName, String secondName, String role){
        return email != null && !email.isEmpty() && password != null && !password.isEmpty() && firstName != null && !firstName.isEmpty()&& secondName != null && !secondName.isEmpty()&& role != null && !role.isEmpty();
    }

    public boolean isValidateDriverData(String id, String email, String firstName, String secondName, String birthDate, String phoneNumber, String rating, String tripAmount, String role){
        return id != null && !id.isEmpty() && email != null && !email.isEmpty() && birthDate != null && !birthDate.isEmpty()
                && firstName != null && !firstName.isEmpty()
                && secondName != null && !secondName.isEmpty()
                && phoneNumber != null && !phoneNumber.isEmpty()
                && rating != null && !rating.isEmpty()
                && tripAmount != null && !tripAmount.isEmpty()
                && role != null && !role.isEmpty();
    }
}
