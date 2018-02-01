package by.runets.buber.application.service.ban;

import by.runets.buber.domain.entity.Ban;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

public class UpdateBanService {
    public boolean update(Ban ban) throws ServiceException {
        boolean isUpdated = false;
        try {
            AbstractDAO abstractDAO = DAOFactory.getInstance().createDAO(DAOType.BAN_DAO_TYPE);
            isUpdated = abstractDAO.update(ban);
        } catch (DAOException e) {
            throw new ServiceException("Update ban service exception", e);
        }
        return isUpdated;
    }
}
