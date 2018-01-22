package by.runets.buber.infrastructure.dao;

import by.runets.buber.domain.entity.Order;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.exception.DAOException;

import java.util.Set;

public interface OrderDAO extends AbstractDAO<Integer, Order> {
    Set<Order> findAllOrdersByDriverId(Integer id) throws DAOException;
    Set<Order> findAllOrdersByPassengerId(Integer id) throws DAOException;
    boolean confirmOrderByDriver(Order order) throws DAOException;
}
