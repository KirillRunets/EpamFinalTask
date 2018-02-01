package by.runets.buber.service;

import by.runets.buber.application.service.order.CollectDriversToOrderService;
import by.runets.buber.application.service.order.PayOrderService;
import by.runets.buber.application.service.statistic.RatingStatisticService;
import by.runets.buber.domain.entity.Account;
import by.runets.buber.domain.entity.Order;
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

    /*@Test
    public void testPayOrderTest() throws ServiceException {
        User from = new User();
        Account fromAccount = new Account();

        fromAccount.setId(1);
        fromAccount.setAccountAmount(10000.0);
        from.setAccount(fromAccount);

        User to = new User();
        to.setId(23);
        Double amount = 1000.0;

        PayOrderService payOrderService = new PayOrderService();
        payOrderService.payOrder(from, to, amount, new Order());
    }*/

    @Test
    public void testCalculateAverageRating() throws ServiceException {
        RatingStatisticService ratingStatisticService = new RatingStatisticService();
        ratingStatisticService.calculateAverageRating(2.0, 5);
    }
}
