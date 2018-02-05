package by.runets.buber.application.service.bonus;

import by.runets.buber.domain.entity.Bonus;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

/**
 * This class provides method to create bonus in DAO.
 */
public class CreateBonusService {
    public boolean create(Bonus bonus) throws ServiceException {
        boolean isCreated = false;
        try {
            AbstractDAO bonusDAO = DAOFactory.getInstance().createDAO(DAOType.BONUS_DAO_TYPE);
            isCreated = bonusDAO.create(bonus);
        } catch (DAOException e) {
            throw new ServiceException("Create bonus service exception", e);
        }
        return isCreated;
    }
}
