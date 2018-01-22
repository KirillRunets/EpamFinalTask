package by.runets.buber.application.service.order;

import by.runets.buber.domain.entity.Order;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.OrderDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

public class MakeOrderService {
    public boolean makeOrder(Order order) throws ServiceException {
        boolean isCreated = false;
        try {
            OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().createDAO(DAOType.ORDER_DAO_TYPE);
            isCreated = order != null && orderDAO.create(order);
        } catch (DAOException e) {
            throw new ServiceException("Make order exception " + e);
        }
        return isCreated;
    }
}
