package by.runets.buber.service;

import by.runets.buber.application.service.user.ReadUserService;
import by.runets.buber.application.service.user.RegisterUserService;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.exception.ConnectionException;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class UserServiceTest {
    @Test
    public void testRegisterUser() throws ConnectionException, DAOException {
        String email = "test@buber.com";
        String password = "Pass123";
        String firstName = "Test";
        String secondName = "Testt";
        String role = "driver";

        RegisterUserService createUserService = new RegisterUserService();
        createUserService.registerUser(email, password, firstName, secondName, role);
    }

    @Test
    public void testReadAllDriversTest() throws ServiceException {
        ReadUserService readUserService = new ReadUserService();
        List<User> userList = readUserService.find(UserRoleType.DRIVER);
        Assert.assertNotNull(userList);
        for (User user : userList){
            Assert.assertEquals(UserRoleType.DRIVER, user.getRole().getRoleName());
        }
    }

    @Test
    public void testReadAllPassengersTest() throws ServiceException {
        ReadUserService readUserService = new ReadUserService();
        List<User> userList = readUserService.find(UserRoleType.PASSENGER);
        Assert.assertNotNull(userList);
        for (User user : userList){
            Assert.assertEquals(UserRoleType.PASSENGER, user.getRole().getRoleName());
        }
    }
}
