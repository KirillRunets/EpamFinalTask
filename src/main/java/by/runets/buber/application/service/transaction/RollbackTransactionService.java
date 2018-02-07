package by.runets.buber.application.service.transaction;

import by.runets.buber.domain.entity.Account;
import by.runets.buber.domain.entity.Transaction;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.AccountDAO;
import by.runets.buber.infrastructure.dao.TransactionDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

import java.math.BigDecimal;
import java.util.Date;

/**
 * This class provides method to rollback user pay transaction.
 */
public class RollbackTransactionService {
    public void rollbackTransaction(Integer transactionId) throws ServiceException {
        Account rollbackTo = null;
        Account rollbackFrom = null;
        BigDecimal amount = null;

        try {
            AccountDAO accountDAO = (AccountDAO) DAOFactory.getInstance().createDAO(DAOType.ACCOUNT_DAO_TYPE);
            TransactionDAO transactionDAO = (TransactionDAO) DAOFactory.getInstance().createDAO(DAOType.TRANSACTION_DAO_TYPE);

            Transaction transaction = transactionDAO.find(transactionId);

            rollbackTo = accountDAO.find(transaction.getFrom().getId());
            rollbackFrom = accountDAO.find(transaction.getTo().getId());
            amount = transaction.getAmount();

            accountDAO.transfer(rollbackFrom, rollbackTo, amount);
            transactionDAO.commitToTransactionStory(new Transaction(rollbackFrom, rollbackTo, new Date(), amount));
        } catch (DAOException e) {
            throw new ServiceException("Rollback transaction service exception", e);
        }
    }
}
