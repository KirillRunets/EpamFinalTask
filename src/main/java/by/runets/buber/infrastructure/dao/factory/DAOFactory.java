package by.runets.buber.infrastructure.dao.factory;

import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.impl.*;
import by.runets.buber.infrastructure.exception.DAOException;

public class DAOFactory {
    private static final DAOFactory INSTANCE = new DAOFactory();

    public static DAOFactory getInstance() {
        return INSTANCE;
    }

    public AbstractDAO createDAO(String type) throws DAOException {
        AbstractDAO abstractDAO = null;
        switch (type){
            case DAOType.USER_DAO_TYPE:
                abstractDAO = UserDAOImpl.getInstance();
                break;
            case DAOType.BAN_DAO_TYPE:
                abstractDAO = BanDAOImpl.getInstance();
                break;
            case DAOType.BONUS_DAO_TYPE:
                abstractDAO = BonusDAOImpl.getInstance();
                break;
            case DAOType.CAR_DAO_TYPE:
                abstractDAO = CarDAOImpl.getInstance();
                break;
            case DAOType.ORDER_DAO_TYPE:
                abstractDAO = OrderDAOImpl.getInstance();
                break;
            case DAOType.ACCOUNT_DAO_TYPE:
                abstractDAO = AccountDAOImpl.getInstance();
                break;
            default:
                throw new DAOException("Wrong DAO type exception");
        }
        return abstractDAO;
    }
}
