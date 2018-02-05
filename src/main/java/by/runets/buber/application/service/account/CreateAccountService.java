package by.runets.buber.application.service.account;

import by.runets.buber.domain.entity.Account;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.AccountDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

/**
 * This class provides method to create account with amount 10000.
 */
public class CreateAccountService {
    public boolean create(Account account) throws ServiceException {
        boolean state = false;
        try {
            AccountDAO accountDAO = (AccountDAO) DAOFactory.getInstance().createDAO(DAOType.ACCOUNT_DAO_TYPE);
            state = accountDAO.create(account);
        } catch (DAOException e) {
            throw new ServiceException("Create account service exception", e);
        }
        return state;
    }
}
