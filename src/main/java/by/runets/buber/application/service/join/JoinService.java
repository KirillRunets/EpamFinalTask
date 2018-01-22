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

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JoinService {
    private final static int NULL = 0;
    public void join(User user) throws DAOException {
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

    private void joinBanToUser(User user) throws DAOException {
        AbstractDAO banDAO = DAOFactory.getInstance().createDAO(DAOType.BAN_DAO_TYPE);
        List<Ban> banList =  banDAO.findAll();
        Ban ban = banList.stream()
                .filter(b -> b.getId() == user.getBan().getId())
                .findFirst().orElse(null);
        user.setBan(ban);
    }

    private void joinOrderToDriver(User user) throws DAOException {
        OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().createDAO(DAOType.ORDER_DAO_TYPE);
        Set<Order> orders = orderDAO.findAllOrdersByDriverId(user.getId());
        user.setOrderSet(orders);
    }

    private void joinOrderToPassenger(User user) throws DAOException {
        OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().createDAO(DAOType.ORDER_DAO_TYPE);
        Set<Order> orders = orderDAO.findAllOrdersByPassengerId(user.getId());
        user.setOrderSet(orders);
    }

    private void joinCarToDriver(User driver) throws DAOException {
        AbstractDAO carDAO = DAOFactory.getInstance().createDAO(DAOType.CAR_DAO_TYPE);
        List<Car> carList = carDAO.findAll();
        carList.stream()
                .filter(car -> car.getCarOwner().getId() == driver.getId())
                .forEach(driver::setCar);
    }

    public List<User> collectUserByRole(List<User> userList, String role){
        return userList.stream()
                .filter(user -> user.getRole().getRoleName().equals(role.toUpperCase()) && user.getBan().getId() == NULL)
                .collect(Collectors.toList());
    }
}
