package by.runets.buber.infrastructure.dao;

import by.runets.buber.domain.entity.Account;
import by.runets.buber.infrastructure.exception.DAOException;

import java.util.Date;

public interface AccountDAO extends AbstractDAO<Integer, Account> {
    void transfer(Account from, Account to, Double payAmount) throws DAOException;
}
