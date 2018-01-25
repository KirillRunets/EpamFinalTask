package by.runets.buber.infrastructure.dao;

import by.runets.buber.domain.entity.Account;
import by.runets.buber.infrastructure.exception.DAOException;

public interface AccountTransactionDAO extends AbstractDAO<Integer, Account> {
    void payOrderTransaction(Account account1, Account account2, Double payAmount) throws DAOException;
}
