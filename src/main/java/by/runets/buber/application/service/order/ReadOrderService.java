package by.runets.buber.application.service.order;

import by.runets.buber.application.service.join.JoinService;
import by.runets.buber.domain.entity.Order;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.OrderDAO;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

import java.util.List;

/**
 * This class provides method to read order from DAO.
 */
public class ReadOrderService {
    public List<Order> readOrderList() throws ServiceException {
        List<Order> orders = null;
        List<User> users = null;
        JoinService joinService = new JoinService();
        try {
            OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().createDAO(DAOType.ORDER_DAO_TYPE);
            UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);

            users = userDAO.findAll();
            orders = orderDAO.findAll();

            joinService.joinDriverToOrder(users, orders);
            joinService.joinPassengerToOrder(users, orders);
        } catch (DAOException e) {
            throw new ServiceException("Read order list service exception", e);
        }
        return orders;
    }
}
