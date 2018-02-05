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
import java.util.logging.Logger;

/**
 * This class provides method to collect free drivers by distance data.
 */
public class CollectDriversToOrderService {
    public Queue<User> collect(User passenger) throws ServiceException {
        List<User> driverList = null;
        Queue<User> driverQueue = null;
        try {
            driverList = new ReadUserService().find(UserRoleType.DRIVER);

            removeDriverWithoutCar(driverList);
            removeBusyDriver(driverList);

            driverList.forEach(driver -> driver.setCurrentLocation(RandomGenerator.generatePoint()));
            driverQueue = new PriorityQueue<>(driverList.size(), new DistanceComparator(passenger));

            driverQueue.addAll(driverList);
        } catch (ServiceException e) {
            throw new ServiceException("Collect drivers to order service exception", e);
        }

        return driverQueue;
    }

    private void removeDriverWithoutCar(List<User> driverList){
       driverList.removeIf(driver -> driver.getCar() == null);
    }

    private void removeBusyDriver(List<User> driverList){
        OrderExistService orderExistService = new OrderExistService();
        driverList.removeIf(driver -> {
            boolean isExist = false;
            try {
                isExist = orderExistService.isExistOrderForUser(driver) != null;
            } catch (ServiceException e) {
                isExist = false;
            }
            return isExist;
        });
    }
}
