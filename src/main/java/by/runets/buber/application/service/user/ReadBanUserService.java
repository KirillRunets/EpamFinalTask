package by.runets.buber.application.service.user;

import by.runets.buber.domain.entity.Ban;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

import java.util.Iterator;
import java.util.List;

public class ReadBanUserService {
    public List<User> read() throws ServiceException {
        List<User> userList = null;
        List<Ban> banList = null;
        try {
            UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
            AbstractDAO abstractDAO = DAOFactory.getInstance().createDAO(DAOType.BAN_DAO_TYPE);
            banList = abstractDAO.findAll();
            userList = userDAO.readBannedUsers();
            for (Ban ban : banList){
                for (User user : userList){
                    if (user.getBan().getId() == ban.getId()){
                        user.setBan(ban);
                    }
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Read banned user exception");
        }
        return userList;
    }
}
