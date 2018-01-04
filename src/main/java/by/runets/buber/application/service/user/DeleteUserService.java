package by.runets.buber.application.service.user;

import by.runets.buber.domain.entity.Role;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.UserRoleDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

public class DeleteUserService {
    public void delete(String id, String role) throws ServiceException {
        try {
            User user = new User(Integer.parseInt(id), new Role(role));
            UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
            UserRoleDAO userRoleDAO = (UserRoleDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);

            userRoleDAO.deleteUserRoleCommunication(user);
            userDAO.delete(user);

        } catch (DAOException e) {
            throw new ServiceException("Delete user exception " + e);
        }

    }
}
