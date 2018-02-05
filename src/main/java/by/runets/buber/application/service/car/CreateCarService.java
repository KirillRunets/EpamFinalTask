package by.runets.buber.application.service.car;

import by.runets.buber.domain.entity.Car;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

import java.util.List;

/**
 * This class provides method to create car in DAO.
 */
public class CreateCarService {
    public boolean create(Car car) throws ServiceException {
        boolean isCreated = true;
        try {
            AbstractDAO carDAO = DAOFactory.getInstance().createDAO(DAOType.CAR_DAO_TYPE);
            isCreated = car != null && !isCarExist(carDAO, car) && carDAO.create(car);
        } catch (DAOException e) {
            throw new ServiceException("Create service exception ", e);
        }
        return isCreated;
    }

    private boolean isCarExist(AbstractDAO carDAO, Car newCar) throws DAOException {
        List<Car> carList = carDAO.findAll();
        return carList.stream()
                .anyMatch(car -> car.getLicensePlate() != null && car.getLicensePlate().equalsIgnoreCase(newCar.getLicensePlate()));
    }
}
