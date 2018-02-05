package by.runets.buber.application.service.ban;

import by.runets.buber.domain.entity.Ban;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;
/**
 * This class provides method to delete ban in DAO.
 */
public class DeleteBanService {
    public boolean delete(Ban ban) throws ServiceException {
        boolean isDeleted = false;
        try {
            AbstractDAO abstractDAO = DAOFactory.getInstance().createDAO(DAOType.BAN_DAO_TYPE);
            isDeleted = abstractDAO.delete(ban);
        } catch (DAOException e) {
            throw new ServiceException("Delete ban service exception", e);
        }
        return isDeleted;
    }
}
