package by.runets.buber.application.service.order;

import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.dao.OrderDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

public class RevokeOrderService {
    public boolean revoke(String role, Integer userId, Integer orderId) throws ServiceException {
        boolean state = false;
        try {
            OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().createDAO(DAOType.ORDER_DAO_TYPE);
            state = role.equalsIgnoreCase(UserRoleType.PASSENGER)
                    ? orderDAO.revokeOrderByPassenger(userId, orderId)
                    : orderDAO.revokeOrderByDriver(userId, orderId);
        } catch (DAOException e) {
            throw new ServiceException("Revoke order service exception", e);
        }
        return state;
    }
}
