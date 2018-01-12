package by.runets.buber.application.service.order;

import by.runets.buber.domain.entity.Order;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

import java.util.List;

public class ReadOrderService {
    public List<Order> readAllOrders() throws ServiceException {
        List<Order> orders = null;
        try {
            AbstractDAO abstractDAO = DAOFactory.getInstance().createDAO(DAOType.ORDER_DAO_TYPE);
            orders = abstractDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException("Read order service exception " + e);
        }
        return orders;
    }
}
