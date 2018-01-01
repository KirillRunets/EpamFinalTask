package by.runets.buber.service;

import by.runets.buber.application.service.user.CreateUserService;
import by.runets.buber.infrastructure.exception.ConnectionException;
import by.runets.buber.infrastructure.exception.DAOException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateUserServiceTest {
    @Test
    public void testRegisterUser() throws ConnectionException, DAOException {
        String email = "test@buber.com";
        String password = "Pass123";
        String firstName = "Test";
        String secondName = "Testt";
        String role = "driver";

        CreateUserService createUserService = new CreateUserService();
        createUserService.registerUser(email, password, firstName, secondName, role);
    }
}
