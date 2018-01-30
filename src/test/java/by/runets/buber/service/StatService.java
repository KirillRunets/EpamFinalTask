package by.runets.buber.service;

import by.runets.buber.application.service.statistic.OrderStatisticService;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.exception.ServiceException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class StatService {
    @Test
    public void testCollectStats() throws  ServiceException {
        OrderStatisticService orderStatisticService = new OrderStatisticService();
        List<Integer> trips =  orderStatisticService.collectStats(3, UserRoleType.PASSENGER);
        List<Integer> actual = Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,3);
        Assert.assertEquals(actual, trips);
    }
}
