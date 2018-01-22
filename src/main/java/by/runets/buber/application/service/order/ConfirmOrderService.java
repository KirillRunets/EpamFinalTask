package by.runets.buber.application.service.order;

import by.runets.buber.domain.entity.Order;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.OrderDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

public class ConfirmOrderService {
    public boolean confirm(Order order) throws ServiceException {
        boolean isConfirmed = false;
        if (!order.isConfirmed()){
            return false;
        }
        try {
            OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().createDAO(DAOType.ORDER_DAO_TYPE);
            isConfirmed = orderDAO.confirmOrderByDriver(order);
        } catch (DAOException e) {
            throw new ServiceException("Confirm order exception " + e);
        }
        return isConfirmed;
    }
}
