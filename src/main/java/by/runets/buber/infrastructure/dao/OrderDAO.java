package by.runets.buber.infrastructure.dao;

import by.runets.buber.domain.entity.Account;
import by.runets.buber.domain.entity.Order;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.exception.DAOException;

import java.util.Set;

public interface OrderDAO extends AbstractDAO<Integer, Order> {
    Set<Order> findAllOrdersByDriverId(Integer id) throws DAOException;
    Set<Order> findAllOrdersByPassengerId(Integer id) throws DAOException;
    Order isExistOrderForUser(User user, String query) throws DAOException;
    boolean revokeOrderByDriver(Integer driverId, Integer orderId) throws DAOException;
    boolean revokeOrderByPassenger(Integer passengerId, Integer orderId) throws DAOException;
    boolean confirmOrderByDriver(Order order) throws DAOException;
    boolean completeOrder(Integer id) throws DAOException;
    boolean setOrderIsPaid(Integer id) throws DAOException;
}
