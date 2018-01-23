package by.runets.buber.application.service.user;

import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.UserRoleDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

import javax.naming.AuthenticationException;

public class RegisterUserService {
    public boolean registerUser(User user) throws ServiceException, AuthenticationException {
        boolean state = false;
        try {
            UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
            UserRoleDAO userRoleDAO = (UserRoleDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
            if (user != null){
                if (!userDAO.isEmailExist(user.getEmail())){
                    if (userDAO.create(user)){
                        state = userRoleDAO.createUserRoleCommunication(user);
                    }
                } else {
                    throw new AuthenticationException("The email is already exist ");
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Register user exception " , e);
        }
        return state;
    }
}
