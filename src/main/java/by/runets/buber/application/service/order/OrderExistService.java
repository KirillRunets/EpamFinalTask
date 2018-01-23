package by.runets.buber.application.service.order;

import by.runets.buber.domain.entity.Order;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.OrderDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

public class OrderExistService {
    public Order isExistOrderForDriver(User driver) throws ServiceException {
        Order order = null;
        try {
            OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().createDAO(DAOType.ORDER_DAO_TYPE);
            order = orderDAO.isExistOrderForDriver(driver);
        } catch (DAOException e) {
            throw new ServiceException("Is exist order for driver exception", e);
        }
        return order;
    }
}
