package by.runets.buber.application.service.ban;

import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

/**
 * This class provides method to revoke ban in DAO.
 */
public class RevokeBanService {
    public boolean revokeBan(User user) throws ServiceException {
        boolean isRevoked = false;
        if (user != null) {
            try {
                UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
                isRevoked = userDAO.setBanToUser(user);
            } catch (DAOException e) {
                throw new ServiceException("Revoke ban service exception ", e);
            }
        }
        return isRevoked;
    }
}
