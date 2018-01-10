package by.runets.buber.service;

import by.runets.buber.application.service.car.CreateCarService;
import by.runets.buber.application.service.car.ReadCarService;
import by.runets.buber.domain.entity.Car;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.infrastructure.util.NumberFormatLocaleFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CarService {
    @Test
    public void testFindValidCarsService() throws ServiceException {
        ReadCarService readCarService = new ReadCarService();
        System.out.println(readCarService.findValidCars());
        Assert.assertNotNull(readCarService.findValidCars());
    }

    @Test
    public void testCreateValidCarService() throws ParseException, ServiceException {
        CreateCarService createCarService = new CreateCarService();
        ReadCarService readCarService = new ReadCarService();
        String numberFormatPattern = NumberFormatLocaleFactory.factory("ru_RU");

        createCarService.create(new Car("Mers", "E-klasse", new SimpleDateFormat(numberFormatPattern).parse("2017-01-01"), new User(6)));

    }
}
