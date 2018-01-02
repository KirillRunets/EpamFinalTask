package by.runets.buber.application.service.user;

import by.runets.buber.domain.entity.Role;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.UserRoleDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.util.PasswordEncrypt;

public class RegisterUserService {
    public boolean registerUser(String email, String password, String firstName, String secondName, String role) throws DAOException {
        User user = new User(email, PasswordEncrypt.encryptPassword(password), firstName, secondName, new Role(role));
        UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
        UserRoleDAO userRoleDAO = (UserRoleDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
        boolean state = false;
        if (userDAO.create(user)){
            if (userRoleDAO.createUserRoleCommunication(user)){
                state = true;
            }
        }

        return state;
    }
}
