package by.runets.buber.application.service.user;

import by.runets.buber.domain.entity.Ban;
import by.runets.buber.domain.entity.Car;
import by.runets.buber.domain.entity.Order;
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
    private final static int NULL = 0;
    public List<User> find(String role) throws ServiceException {
        List<User> userList = null;
        List<User> userListByRole = null;

        try {
            UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
            userList = userDAO.findAll();
            userListByRole = collectUserByRole(userList, role);

            if (role.equals(UserRoleType.DRIVER)) {
                setCarToDriver(userListByRole);
            } else {
                setOrderToPassenger(userListByRole);
            }
        } catch (DAOException e) {
            throw new ServiceException("Find user throw exception " + e);
        }

        return userListByRole;
    }

    public User find(int id) throws ServiceException {
        User user = null;
        try {
            UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
            user = userDAO.find(id);
        } catch (DAOException e) {
            throw new ServiceException("Find user service " + e);
        }
        return user;
    }

    private List<User> collectUserByRole(List<User> userList, String role){
        return userList.stream()
                .filter(user -> user.getRole().getRoleName().equals(role.toUpperCase()) && user.getBan().getId() == NULL)
                .collect(Collectors.toList());
    }

    private void setCarToDriver(List<User> userListByRole) throws DAOException {
        AbstractDAO carDAO = DAOFactory.getInstance().createDAO(DAOType.CAR_DAO_TYPE);
        List<Car> carList = carDAO.findAll();
        userListByRole.forEach(driver -> {
            carList.stream()
                    .filter(car -> car.getCarOwner().getId() == driver.getId())
                    .forEach(driver::setCar);
        });
    }

    private void setOrderToPassenger(List<User> userListByRole) throws DAOException {
        AbstractDAO orderDAO = DAOFactory.getInstance().createDAO(DAOType.ORDER_DAO_TYPE);
        List<Order> orderList = orderDAO.findAll();
        userListByRole.forEach(passenger -> {
            orderList.stream()
                    .filter(order -> order.getPassenger().isPresent() && order.getPassenger().get().getId() == passenger.getId())
                    .forEach(passenger::setOrder);
        });
    }
}
