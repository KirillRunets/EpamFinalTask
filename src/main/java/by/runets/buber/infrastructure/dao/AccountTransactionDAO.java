package by.runets.buber.infrastructure.dao;

import by.runets.buber.domain.entity.Account;
import by.runets.buber.infrastructure.exception.DAOException;

import java.util.Date;

public interface AccountTransactionDAO extends AbstractDAO<Integer, Account> {
    void payOrderTransaction(Account from, Account to, Double payAmount) throws DAOException;
    void commitToTransactionStory(Account from, Account to, Double payAmount, Date transactionDate) throws DAOException;

}
