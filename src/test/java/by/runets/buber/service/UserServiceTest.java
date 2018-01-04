package by.runets.buber.service;

import by.runets.buber.application.service.user.DeleteUserService;
import by.runets.buber.application.service.user.ReadUserService;
import by.runets.buber.application.service.user.RegisterUserService;
import by.runets.buber.application.service.user.UpdateUserService;
import by.runets.buber.domain.entity.Car;
import by.runets.buber.domain.entity.Role;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.exception.ConnectionException;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.infrastructure.util.PasswordEncrypt;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class UserServiceTest {
    private User createUser;
    private User deleteUser;
    private User updateUser;
    @BeforeClass
    public void init(){
        createUser = new User("testEmail@gmial.com", PasswordEncrypt.encryptPassword("Pass123"), "Test", "Test", new Role("driver"));
        deleteUser = new User(20, new Role("driver"));
        updateUser = new User(20, "newEmail@gmail.com", "Passs123", "Test", "Test", new Role("driver"));
    }


    @Test
    public void testRegisterUser() throws ConnectionException, DAOException {
        RegisterUserService createUserService = new RegisterUserService();
        createUserService.registerUser(createUser);
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

    @Test
    public void deleteUserTest() throws ServiceException {
        String id = "2";
        String role = "driver";
        DeleteUserService deleteUserService = new DeleteUserService();
        deleteUserService.delete(id, role);
    }

    @Test
    public void updateUserService() throws ServiceException {
        UpdateUserService updateUserService = new UpdateUserService();
        updateUserService.update(updateUser);
    }

    @Test
    public void updateDriverCarService() throws ServiceException {
        UpdateUserService updateUserService = new UpdateUserService();
        Car car = new Car();
        car.setId(5);
        car.setMark("Hyundai");
        car.setModel("Solaris");
        car.setCarOwner(updateUser);
        updateUser.setCar(car);
        updateUserService.update(updateUser);
    }
}
