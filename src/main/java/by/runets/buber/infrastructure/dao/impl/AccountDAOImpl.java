package by.runets.buber.infrastructure.dao.impl;

import by.runets.buber.domain.entity.Account;
import by.runets.buber.infrastructure.connection.ConnectionPool;
import by.runets.buber.infrastructure.connection.ProxyConnection;
import by.runets.buber.infrastructure.constant.DatabaseQueryConstant;
import by.runets.buber.infrastructure.dao.AccountTransactionDAO;
import by.runets.buber.infrastructure.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountDAOImpl implements AccountTransactionDAO {
    private static AccountDAOImpl instance;
    private static Lock lock = new ReentrantLock();
    private ProxyConnection proxyConnectionTo;
    private ProxyConnection proxyConnectionFrom;

    private AccountDAOImpl(){}

    public static AccountDAOImpl getInstance() throws DAOException {
        lock.lock();
        try{
            if (instance == null){
                instance = new AccountDAOImpl();
                instance.getProxyConnectionFrom();
                instance.getProxyConnectionTo();
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }

    private void getProxyConnectionTo() throws DAOException {
        try {
            proxyConnectionTo = ConnectionPool.getInstance().getConnection();
            proxyConnectionTo.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DAOException("ProxyConnectionTo exception", e);
        }
    }

    private void getProxyConnectionFrom() throws DAOException {
        try {
            proxyConnectionFrom = ConnectionPool.getInstance().getConnection();
            proxyConnectionFrom.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DAOException("ProxyConnectionFrom exception", e);
        }
    }

    @Override
    public void payOrderTransaction(Account fromAccount, Account toAccount, Double payAmount) throws DAOException {
        PreparedStatement fromAccountStatement = null;
        PreparedStatement toAccountStatement = null;

        double accountFrom = 0.0;
        double accountTo = 0.0;
        double resultFrom= 0.0;
        double resultTo = 0.0;

        if (payAmount <= 0){
            throw new DAOException("Invalid pay balance balance");
        }

        try {
            fromAccountStatement = proxyConnectionFrom.prepareStatement(DatabaseQueryConstant.SELECT_PAY_AMOUNT_FROM_ACCOUNT);
            fromAccountStatement.setInt(1, fromAccount.getAccountId());
            toAccountStatement = proxyConnectionTo.prepareStatement(DatabaseQueryConstant.SELECT_PAY_AMOUNT_FROM_ACCOUNT);
            fromAccountStatement.setInt(1, toAccount.getAccountId());

            ResultSet resultSetFrom = fromAccountStatement.executeQuery();
            ResultSet resultSetTo = fromAccountStatement.executeQuery();

            if (resultSetFrom.next()){
                accountFrom = resultSetFrom.getInt("account_amount");
            } else {
                throw new DAOException("Account with account_id " + fromAccount.getAccountId() + " not found");
            }

            if (accountFrom >= payAmount){
                resultFrom = accountFrom - payAmount;
            } else {
                throw new DAOException("Invalid balance");
            }

            if (resultSetFrom.next()){
                accountTo = resultSetTo.getInt("account_amount");
            } else {
                throw new DAOException("Account with account_id " + toAccount.getAccountId() + " not found");
            }

            resultTo = accountTo + payAmount;

            fromAccountStatement.executeUpdate("UPDATE account SET account_amount=" + resultFrom);
            toAccountStatement.executeUpdate("UPDATE account SET account_amount=" + resultTo);

            proxyConnectionFrom.commit();
            proxyConnectionTo.commit();
        } catch (SQLException e) {
            try {
                proxyConnectionFrom.rollback();
                proxyConnectionTo.rollback();
            } catch (SQLException e1) {
                throw new DAOException("Rollback exception in DAO", e);
            }
            throw new DAOException("Transaction exception in DAO", e);
        } finally {
            close(fromAccountStatement, proxyConnectionFrom);
            close(toAccountStatement, proxyConnectionTo);
        }
    }

    @Override
    public List<Account> findAll() throws DAOException {
        throw new UnsupportedOperationException("This method is prohibited");
    }

    @Override
    public Account find(Integer id) throws DAOException {
        throw new UnsupportedOperationException("This method is prohibited");
    }

    @Override
    public boolean delete(Account entity) throws DAOException {
        return false;
    }

    @Override
    public boolean create(Account entity) throws DAOException {
        throw new UnsupportedOperationException("This method is prohibited");
    }

    @Override
    public boolean update(Account entity) throws DAOException {
        throw new UnsupportedOperationException("This method is prohibited");
    }
}
