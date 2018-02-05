package by.runets.buber.application.service.order;

import by.runets.buber.domain.entity.Order;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.constant.DatabaseQueryConstant;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.dao.OrderDAO;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

/**
 * This class provides method which check is exist order for user.
 */
public class OrderExistService {
    public Order isExistOrderForUser(User user) throws ServiceException {
        Order order = null;
        try {
            OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().createDAO(DAOType.ORDER_DAO_TYPE);
            order = user.getRole().getRoleName().equalsIgnoreCase(UserRoleType.DRIVER)
                    ? orderDAO.isExistOrderForUser(user, DatabaseQueryConstant.IS_EXIST_ORDER_FOR_DRIVER)
                    : orderDAO.isExistOrderForUser(user, DatabaseQueryConstant.IS_EXIST_ORDER_FOR_PASSENGER);
        } catch (DAOException e) {
            throw new ServiceException("Is exist order for user exception", e);
        }
        return order;
    }
}
