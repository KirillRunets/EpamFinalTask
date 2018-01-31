package by.runets.buber.infrastructure.dao;

import by.runets.buber.domain.entity.Transaction;
import by.runets.buber.infrastructure.exception.DAOException;

public interface TransactionDAO extends AbstractDAO<Integer, Transaction>{
    void commitToTransactionStory(Transaction transaction) throws DAOException;
}
