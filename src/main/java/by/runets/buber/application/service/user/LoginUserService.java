package by.runets.buber.application.service.user;

import by.runets.buber.application.service.join.JoinService;
import by.runets.buber.domain.entity.Car;
import by.runets.buber.domain.entity.Order;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DatabaseQueryConstant;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.OrderDAO;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.EncryptorException;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.infrastructure.util.PasswordEncrypt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LoginUserService {
    private final static Logger LOGGER = LogManager.getLogger(LoginUserService.class);
    public User authenticateUser(String login, String password) throws ServiceException {
        UserDAO dao = null;
        User user = null;
        try {
            dao = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
            JoinService joinService = new JoinService();
            user = dao.checkEmailPassword(login, PasswordEncrypt.encryptPassword(password));
            if (user != null){
                joinService.join(user);
            }
        } catch (DAOException e) {
            throw new ServiceException("Authenticate user service exception", e);
        }
        return user;
    }
}
