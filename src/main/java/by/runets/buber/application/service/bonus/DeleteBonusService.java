package by.runets.buber.application.service.bonus;

import by.runets.buber.domain.entity.Bonus;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;
/**
 * This class provides method to delete bonus in DAO.
 */
public class DeleteBonusService {
    public boolean delete(Bonus bonus) throws ServiceException {
        boolean isDeleted = false;
        try {
            AbstractDAO bonusDAO = DAOFactory.getInstance().createDAO(DAOType.BONUS_DAO_TYPE);
            isDeleted = bonusDAO.delete(bonus);
        } catch (DAOException e) {
            throw new ServiceException("Delete bonus service exception", e);
        }
        return isDeleted;
    }
}
