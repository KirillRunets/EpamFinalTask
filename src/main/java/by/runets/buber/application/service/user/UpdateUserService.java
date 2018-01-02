package by.runets.buber.application.service.user;

import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.UserRoleDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

public class UpdateUserService {
    public void update(User user) throws ServiceException {
        try {
            UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
            UserRoleDAO userRoleDAO = (UserRoleDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);

            if (user.getRole() != null){
                userRoleDAO.updateUserRoleCommunication(user);
            }
            userDAO.update(user);

        } catch (DAOException e) {
            throw new ServiceException("Update user exception " + e);
        }
    }
}
