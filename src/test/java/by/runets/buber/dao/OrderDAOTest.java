package by.runets.buber.dao;

import by.runets.buber.domain.entity.Order;
import by.runets.buber.domain.entity.Point;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.OrderDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OrderDAOTest {
    @Test
    public void testCreateOrder() throws DAOException {
        Order order = new Order();
        order.setDistance(12.5);
        order.setTripTime((long) 12);
        order.setTripCost(BigDecimal.valueOf(12.5));
        order.setStartPoint(new Point(1.0, 1.0));
        order.setDestinationPoint(new Point(2.0, 2.0));
        order.setOrderDate(new Date());
        order.setPassenger(new User(3));
        order.setDriver(new User(23));
        OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().createDAO(DAOType.ORDER_DAO_TYPE);
        Assert.assertTrue(orderDAO.create(order));
    }

    @Test
    public void confirmOrderByDriver() throws DAOException {
        Order order = new Order();
        order.setId(23);
        order.setConfirmed(true);
        OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().createDAO(DAOType.ORDER_DAO_TYPE);
        Assert.assertTrue(orderDAO.confirmOrderByDriver(order));
    }

    @Test
    public void testRevokeOrderByDriver() throws DAOException {
        int driverId = 23;
        int orderId = 24;
        OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().createDAO(DAOType.ORDER_DAO_TYPE);
        Assert.assertTrue(orderDAO.revokeOrderByDriver(driverId, orderId));
    }

    @Test
    public void testFindAllOrdersByPassengerId() throws DAOException {
        OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().createDAO(DAOType.ORDER_DAO_TYPE);
        orderDAO.findAllOrdersByPassengerId(3);
    }
}
