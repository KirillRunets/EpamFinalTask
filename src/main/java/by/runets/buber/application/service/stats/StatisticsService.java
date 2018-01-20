package by.runets.buber.application.service.stats;

import by.runets.buber.domain.entity.Order;
import by.runets.buber.domain.enumeration.MonthEnum;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticsService {
    private final static Logger LOGGER = LogManager.getLogger(StatisticsService.class);
    public List<Integer> collectStats(Integer id, String role) throws DAOException {
        AbstractDAO orderDAO = DAOFactory.getInstance().createDAO(DAOType.ORDER_DAO_TYPE);
        List<Integer> stats = new LinkedList<>();

        List<Order> orders = orderDAO.findAll();
        orders = role.equalsIgnoreCase(UserRoleType.DRIVER) ? collectOrdersByDriver(id, orders) : collectOrdersByPassenger(id, orders);

        Map<MonthEnum, Integer> map = calculateTripAmountInMonth(orders);
        map.keySet().forEach(key ->{
            Arrays.stream(MonthEnum.values())
                    .filter(key::equals)
                    .forEach(monthEnum -> stats.add(map.get(monthEnum)));
        });

        return stats;
    }

    private List<Order> collectOrdersByPassenger(Integer id, List<Order> orders){
        return orders.stream()
                .filter(order -> order.getPassenger().isPresent()
                        && order.getPassenger().get().getId() == id)
                .collect(Collectors.toList());
    }

    private List<Order> collectOrdersByDriver(Integer id, List<Order> orders){
        return orders.stream()
                .filter(order -> order.getDriver().isPresent()
                        && order.getDriver().get().getId() == id)
                .collect(Collectors.toList());
    }

    private Map<MonthEnum, Integer> calculateTripAmountInMonth(List<Order> orders){
        Map<MonthEnum, Integer> map = new LinkedHashMap<>();
        Arrays.stream(MonthEnum.values())
                .forEach(monthEnum -> {
                    int tripAmount = (int) orders.stream()
                            .filter(order -> getMonth(order.getOrderDate()) == monthEnum.ordinal())
                            .count();
                    map.put(monthEnum, tripAmount);
                });
        return map;
    }

    private int getMonth(Date tripDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(tripDate);
        return cal.get(Calendar.MONTH);
    }

}
