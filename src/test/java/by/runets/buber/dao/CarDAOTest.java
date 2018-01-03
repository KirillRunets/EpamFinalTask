package by.runets.buber.dao;

import by.runets.buber.application.service.user.ReadUserService;
import by.runets.buber.domain.entity.Car;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CarDAOTest {
    @Test
    public void testFindCarByOwner() throws ServiceException, DAOException {
        AbstractDAO dao = DAOFactory.getInstance().createDAO(DAOType.CAR_DAO_TYPE);
        ReadUserService readUserService = new ReadUserService();
        List<User> drivers = readUserService.find(UserRoleType.DRIVER);
        for (User driver : drivers){
           Assert.assertNotNull((Car)dao.find(driver.getId()));
        }

    }
}
