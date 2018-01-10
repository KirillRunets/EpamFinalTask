package by.runets.buber.application.service.ban;

import by.runets.buber.domain.entity.Ban;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

import java.util.List;

public class ReadBanService {
    public List<Ban> find() throws ServiceException {
        AbstractDAO abstractDAO = null;
        List<Ban> banList = null;
        try {
            abstractDAO = DAOFactory.getInstance().createDAO(DAOType.BAN_DAO_TYPE);
            banList = (List<Ban>) abstractDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException("Find all ban exception");
        }
        return banList;
    }
}
