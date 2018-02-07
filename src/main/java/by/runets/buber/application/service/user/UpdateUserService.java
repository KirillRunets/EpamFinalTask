package by.runets.buber.application.service.user;

import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

/**
 * This class provides method to update user in DAO.
 */
public class UpdateUserService {
    public boolean update(User user) throws ServiceException {
        boolean isUpdated = false;
        try {
            UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
            isUpdated = userDAO.update(user);
        } catch (DAOException e) {
            throw new ServiceException("Update user service exception", e);
        }
        return isUpdated;
    }
}


