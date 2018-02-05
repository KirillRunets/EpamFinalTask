package by.runets.buber.application.service.ban;

import by.runets.buber.domain.entity.Ban;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

/**
 * This class provides method to create ban in DAO.
 */
public class CreateBanService {
    public boolean create(Ban ban) throws ServiceException {
        boolean isCreated = false;
        try {
            AbstractDAO abstractDAO = DAOFactory.getInstance().createDAO(DAOType.BAN_DAO_TYPE);
            isCreated = abstractDAO.create(ban);
        } catch (DAOException e) {
            throw new ServiceException("Create ban service exception", e);
        }
        return isCreated;
    }
}
