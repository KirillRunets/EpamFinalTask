package by.runets.buber.application.service.bonus;

import by.runets.buber.domain.entity.Bonus;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

public class UpdateBonusService {
    public boolean update(Bonus bonus) throws ServiceException {
        boolean isUpdated = false;
        try {
            AbstractDAO bonusDAO = DAOFactory.getInstance().createDAO(DAOType.BONUS_DAO_TYPE);
            isUpdated = bonusDAO.update(bonus);
        } catch (DAOException e) {
            throw new ServiceException("Update bonus service exception", e);
        }
        return isUpdated;
    }
}
