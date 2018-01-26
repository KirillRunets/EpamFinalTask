package by.runets.buber.application.service.order;

import by.runets.buber.domain.entity.Account;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.AccountTransactionDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

public class PayOrderService {
    public void payOrder(Account from, Account to, Double amount) throws ServiceException {
        try {
            AccountTransactionDAO accountTransactionDAO = (AccountTransactionDAO) DAOFactory.getInstance().createDAO(DAOType.ACCOUNT_DAO_TYPE);
            accountTransactionDAO.payOrderTransaction(from, to, amount);
        } catch (DAOException e) {
            throw new ServiceException("Pay order service exception");
        }
    }
}
