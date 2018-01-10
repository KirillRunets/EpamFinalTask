package by.runets.buber.application.service.car;

import by.runets.buber.domain.entity.Car;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

public class CreateCarService {
    public void create(Car car) throws ServiceException {
        try {
            AbstractDAO carDAO = DAOFactory.getInstance().createDAO(DAOType.CAR_DAO_TYPE);
            if (car != null && carDAO != null){
                carDAO.create(car);
            }
        } catch (DAOException e) {
            throw new ServiceException("Create service exception " + e);
        }
    }
}
