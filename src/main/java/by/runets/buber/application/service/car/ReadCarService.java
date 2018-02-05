package by.runets.buber.application.service.car;

import by.runets.buber.domain.entity.Car;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class provides method to read car in DAO.
 */
public class ReadCarService {
    public Car findCarByOwner(Integer id) throws ServiceException {
        Car car = null;
        try {
            AbstractDAO abstractDAO = DAOFactory.getInstance().createDAO(DAOType.CAR_DAO_TYPE);
            car = (Car) abstractDAO.find(id);
        } catch (DAOException e) {
            throw new ServiceException("Find car exception ", e);
        }
        return car;
    }

    public List<Car> findAllCars() throws ServiceException {
        List<Car> cars = null;
        try {
            AbstractDAO abstractDAO = DAOFactory.getInstance().createDAO(DAOType.CAR_DAO_TYPE);
            cars = abstractDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException("Find all cars exception ", e);
        }
        return cars;
    }

    public List<Car> findCars() throws ServiceException {
        List<Car> carList = null;
        List<User> userList = null;
        try {
            UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
            AbstractDAO carDAO = DAOFactory.getInstance().createDAO(DAOType.CAR_DAO_TYPE);

            userList = userDAO.findAll();
            carList = carDAO.findAll();

            joinUserToCar(userList, carList);
        } catch (DAOException e) {
            throw new ServiceException("Find valid cars exception ", e);
        }
        return filterValidCar(carList);
    }

    public Car findCarById(int id) throws ServiceException {
        List<Car> cars = null;
        try {
            AbstractDAO carDAO = DAOFactory.getInstance().createDAO(DAOType.CAR_DAO_TYPE);
            cars = carDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException("Find all cars exception ", e);
        }
        return getCarFromList(cars, id);
    }

    private void joinUserToCar(List<User> userList, List<Car> carList) {
        userList.forEach(user -> {
            carList.stream()
                    .filter(car -> car.getCarOwner().getId() == user.getId())
                    .forEach(car -> car.setCarOwner(user));
        });
    }

    private List<Car> filterValidCar(List<Car> carList) {
        carList = carList.stream()
                .filter(car -> car.getCarOwner().getRole().getRoleName().equalsIgnoreCase(UserRoleType.ADMIN))
                .collect(Collectors.toList());
        carList.sort(Comparator.comparing(Car::getMark).thenComparing(Car::getModel));
        return carList;
    }

    private Car getCarFromList(List<Car> cars, int id) {
        return cars.stream()
                .filter(car -> car.getId() == id)
                .findFirst()
                .get();
    }

}
