package by.runets.buber.application.service.bonus;

import by.runets.buber.domain.entity.Bonus;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

import java.util.List;

/**
 * This class provides method to read bonus in DAO.
 */
public class ReadBonusService {
    public List<Bonus> readAll() throws ServiceException {
        List<Bonus> listBonus = null;
        try {
            AbstractDAO bonusDAO = DAOFactory.getInstance().createDAO(DAOType.BONUS_DAO_TYPE);
            listBonus = bonusDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException("Find all bonuses service exception", e);
        }
        return listBonus;
    }

    public Bonus read(Integer id) throws ServiceException {
        Bonus bonus = null;
        try {
            AbstractDAO bonusDAO = DAOFactory.getInstance().createDAO(DAOType.BONUS_DAO_TYPE);
            bonus = (Bonus) bonusDAO.find(id);
        } catch (DAOException e) {
            throw new ServiceException("Find bonus service exception", e);
        }
        return bonus;
    }
}
