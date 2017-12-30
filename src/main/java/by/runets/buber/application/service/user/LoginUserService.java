package by.runets.buber.application.service.user;

import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.EncryptorException;
import by.runets.buber.infrastructure.util.PasswordEncrypt;

public class LoginUserService {
    public User authenticateUser(String login, String password) throws DAOException {
        UserDAO dao = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
        password = PasswordEncrypt.encryptPassword(password);
        return dao.checkEmailPassword(login, password);
    }

}
