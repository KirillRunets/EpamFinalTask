package by.runets.buber.application.service.user;

import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.infrastructure.util.PasswordEncrypt;

import javax.xml.ws.Service;

public class ChangePasswordService {
    public boolean change(String oldPassword, String newPassword, int userId) throws ServiceException {
        UserDAO userDAO = null;
        int updateRow = 0;
        String password = null;
        try {
            userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
            password = userDAO.findPassword(userId);
            if (password != null && password.equals(PasswordEncrypt.encryptPassword(oldPassword))){
                updateRow = userDAO.changePassword(newPassword, userId);
            }
        } catch (DAOException e) {
            throw new ServiceException("Change password service exception " + e);
        }

        return updateRow != 0;
    }
}
