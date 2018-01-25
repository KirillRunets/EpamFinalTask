package by.runets.buber.application.service.user;

import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.UserRoleDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.dao.impl.CarDAOImpl;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

public class UpdateUserService {
    public boolean update(User user) throws ServiceException {
        boolean isUpdated = false;
        try {
            UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);

            switch (user.getRole().getRoleName().toUpperCase()){
                case UserRoleType.DRIVER:
                    updateDriverBusinessTool(user);
                    break;
                case UserRoleType.PASSENGER:
                    updatePassengerBusinessTool(user);
                    break;
            }
            isUpdated = userDAO.update(user);

        } catch (DAOException e) {
            throw new ServiceException("Update user exception " , e);
        }
        return isUpdated;
    }



    private void changeUserRole(User user) throws DAOException {
        UserRoleDAO userRoleDAO = (UserRoleDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
        userRoleDAO.updateUserRoleCommunication(user);
    }

    private void updateDriverBusinessTool(User user) throws DAOException {
        AbstractDAO carDAO = DAOFactory.getInstance().createDAO(DAOType.CAR_DAO_TYPE);
        if (user.getCar() != null){
            carDAO.update(user.getCar());
        }
    }

    private void updatePassengerBusinessTool(User user){

    }
}


