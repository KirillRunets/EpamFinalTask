package by.runets.buber.application.service.bonus;

import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

public class SetBonusService {
    public boolean setBonusToUser(User user) throws ServiceException {
        boolean state = false;
        try {
            UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
            if (user != null){
                state = userDAO.setBonusToUser(user);
            }
        } catch (DAOException e) {
            throw new ServiceException("Set bonus to user service exception", e);
        }
        return state;
    }
}
