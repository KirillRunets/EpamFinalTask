package by.runets.buber.application.service.order;

import by.runets.buber.application.service.order.comparator.DistanceComparator;
import by.runets.buber.application.service.user.ReadUserService;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.infrastructure.util.RandomGenerator;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class CollectDriversToOrderService {
    public Queue<User> collect(User passenger) throws ServiceException {
        List<User> driverList = new ReadUserService().find(UserRoleType.DRIVER);
        driverList.forEach(driver -> driver.setCurrentLocation(RandomGenerator.generatePoint()));

        Queue<User> userQueue = new PriorityQueue<>(driverList.size(), new DistanceComparator(passenger));

        userQueue.addAll(driverList);

        return userQueue;
    }
}
