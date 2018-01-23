package by.runets.buber.application.service.join;

import by.runets.buber.domain.entity.Ban;
import by.runets.buber.domain.entity.Car;
import by.runets.buber.domain.entity.Order;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.OrderDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JoinService {
    private final static int NULL = 0;
    public void join(User user) throws ServiceException {
        joinBanToUser(user);
        switch (user.getRole().getRoleName().toUpperCase()){
            case UserRoleType.DRIVER:
                joinOrderToDriver(user);
                joinCarToDriver(user);
                break;
            case UserRoleType.PASSENGER:
                joinOrderToPassenger(user);
                break;
        }
    }

    private void joinBanToUser(User user) throws ServiceException {
        AbstractDAO banDAO = null;
        try {
            banDAO = DAOFactory.getInstance().createDAO(DAOType.BAN_DAO_TYPE);
            List<Ban> banList =  banDAO.findAll();
            banList.stream()
                    .filter(b -> b.getId() == user.getBan().getId())
                    .forEach(user::setBan);
        } catch (DAOException e) {
            throw new ServiceException("Join ban to user exception", e);
        }
    }

    private void joinOrderToDriver(User user) throws ServiceException {
        OrderDAO orderDAO = null;
        try {
            orderDAO = (OrderDAO) DAOFactory.getInstance().createDAO(DAOType.ORDER_DAO_TYPE);
            Set<Order> orders = orderDAO.findAllOrdersByDriverId(user.getId());
            user.setOrderSet(orders);
        } catch (DAOException e) {
            throw new ServiceException("Join order to driver exception", e);
        }
    }

    private void joinOrderToPassenger(User user) throws ServiceException {
        try {
            OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().createDAO(DAOType.ORDER_DAO_TYPE);
            Set<Order> orders = orderDAO.findAllOrdersByPassengerId(user.getId());
            user.setOrderSet(orders);
        } catch (DAOException e) {
            throw new ServiceException("Join order to passenger exception", e);
        }
    }

    private void joinCarToDriver(User driver) throws ServiceException {
        try {
            AbstractDAO carDAO = DAOFactory.getInstance().createDAO(DAOType.CAR_DAO_TYPE);
            List<Car> carList = carDAO.findAll();
            carList.stream()
                    .filter(car -> car.getCarOwner().getId() == driver.getId())
                    .forEach(driver::setCar);
        } catch (DAOException e) {
            throw new ServiceException("Join car to driver exception", e);
        }
    }

    public List<User> collectUserByRole(List<User> userList, String role){
        return userList.stream()
                .filter(user -> user.getRole().getRoleName().equals(role.toUpperCase()) && user.getBan().getId() == NULL)
                .collect(Collectors.toList());
    }
}
