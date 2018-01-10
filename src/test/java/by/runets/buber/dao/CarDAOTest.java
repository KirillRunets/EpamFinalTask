package by.runets.buber.dao;

import by.runets.buber.application.service.car.CreateCarService;
import by.runets.buber.application.service.user.ReadUserService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.Car;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.infrastructure.util.NumberFormatLocaleFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Test
    public void testCreateValidCar() throws DAOException, ParseException, ServiceException {
        String numberFormatPattern = NumberFormatLocaleFactory.factory("ru_RU");
        AbstractDAO carDAO = DAOFactory.getInstance().createDAO(DAOType.CAR_DAO_TYPE);
        carDAO.create(new Car("Mers", "E-klasse", new SimpleDateFormat(numberFormatPattern).parse("2017-01-01"), new User(6)));

    }
}
