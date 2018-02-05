package by.runets.buber.application.service.order;

import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.OrderDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

/**
 * This class provides method to complete order in DAO.
 */
public class CompleteOrderService {
    public boolean complete(Integer orderId) throws ServiceException {
        boolean state = false;
        try {
            OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().createDAO(DAOType.ORDER_DAO_TYPE);
            state = orderDAO.completeOrder(orderId);
        } catch (DAOException e) {
            throw new ServiceException("Complete order service exception", e);
        }
        return state;
    }
}
