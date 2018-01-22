package by.runets.buber.service;

import by.runets.buber.application.service.order.CollectDriversToOrderService;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.infrastructure.util.RandomGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderService {
    @Test
    public void testCollectDrivers() throws ServiceException {
        User user = new User();
        user.setCurrentLocation(RandomGenerator.generatePoint());

        CollectDriversToOrderService collectDriversToOrderService = new CollectDriversToOrderService();

        Assert.assertNotNull(collectDriversToOrderService.collect(user));
    }
}
