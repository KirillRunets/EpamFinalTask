package by.runets.buber.application.service.user;

import by.runets.buber.application.service.join.JoinService;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

import java.util.List;

public class ReadUserService {
    private final static int NULL = 0;

    public List<User> find(String role) throws ServiceException {
        List<User> userList = null;
        List<User> userListByRole = null;
        JoinService joinService = new JoinService();
        try {
            UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
            userList = userDAO.findAll();
            userListByRole = joinService.collectUserByRole(userList, role);
            for (User user : userListByRole){
                joinService.join(user);
            }
        } catch (DAOException e) {
            throw new ServiceException("Find user throw exception " , e);
        }

        return userListByRole;
    }

    public User find(int id) throws ServiceException {
        User user = null;
        JoinService joinService = new JoinService();

        try {
            UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
            user = userDAO.find(id);
            joinService.join(user);
        } catch (DAOException e) {
            throw new ServiceException("Find user service " , e);
        }
        return user;
    }
}
