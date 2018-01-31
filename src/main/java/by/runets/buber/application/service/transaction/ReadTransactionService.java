package by.runets.buber.application.service.transaction;

import by.runets.buber.domain.entity.Transaction;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.TransactionDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

import java.util.List;

public class ReadTransactionService {
    public List<Transaction> read() throws ServiceException {
        List<Transaction> transactions = null;
        try {
            TransactionDAO transactionDAO = (TransactionDAO) DAOFactory.getInstance().createDAO(DAOType.TRANSACTION_DAO_TYPE);
            transactions = transactionDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException("Read transactions dao exception", e);
        }
        return transactions;
    }
}
