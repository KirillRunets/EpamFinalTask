package by.runets.buber.application.service.user;

import by.runets.buber.application.service.car.ReadCarService;
import by.runets.buber.domain.entity.Car;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReadUserService {
    public List<User> find(String role) throws ServiceException {
        List<User> userList = new ArrayList<>();
        List<User> userListByRole = new ArrayList<>();


        try {
            UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
            AbstractDAO abstractDAO = DAOFactory.getInstance().createDAO(DAOType.CAR_DAO_TYPE);
            userList = userDAO.findAll();

            userListByRole = userList
                    .stream()
                    .filter(user -> user.getRole().getRoleName().equals(role.toUpperCase()))
                    .collect(Collectors.toList());
            if (role.equals(UserRoleType.DRIVER)){
                for (User driver : userListByRole){
                    Car car = (Car) abstractDAO.find(driver.getId());
                    if (car != null){
                        driver.setCar(car);
                    }
                }
            }

        } catch (DAOException e) {
            throw new ServiceException("Find user throw exception " + e);
        }

        return userListByRole;
    }
}
