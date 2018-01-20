package by.runets.buber.service;

import by.runets.buber.application.service.statistics.StatisticsService;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class StatService {
    @Test
    public void testCollectStats() throws  ServiceException {
        StatisticsService statisticsService = new StatisticsService();
        List<Integer> trips =  statisticsService.collectStats(3, UserRoleType.PASSENGER);
        List<Integer> actual = Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,3);
        Assert.assertEquals(actual, trips);
    }
}
