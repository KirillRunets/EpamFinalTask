package by.runets.buber.infrastructure.dao.impl;

import by.runets.buber.domain.entity.Account;
import by.runets.buber.infrastructure.connection.ConnectionPool;
import by.runets.buber.infrastructure.connection.ProxyConnection;
import by.runets.buber.infrastructure.constant.DatabaseQueryConstant;
import by.runets.buber.infrastructure.dao.AccountDAO;
import by.runets.buber.infrastructure.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountDAOImpl implements AccountDAO {
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
            fromAccountStatement.setInt(1, fromAccount.getId());
            toAccountStatement = proxyConnectionTo.prepareStatement(DatabaseQueryConstant.SELECT_PAY_AMOUNT_FROM_ACCOUNT);
            toAccountStatement.setInt(1, toAccount.getId());

            ResultSet resultSetFrom = fromAccountStatement.executeQuery();
            ResultSet resultSetTo = toAccountStatement.executeQuery();

            if (resultSetFrom.next()){
                accountFrom = resultSetFrom.getDouble("account_amount");
            } else {
                throw new DAOException("Account with account_id " + fromAccount.getId() + " not found");
            }

            if (accountFrom >= payAmount){
                resultFrom = accountFrom - payAmount;
            } else {
                throw new DAOException("Invalid balance");
            }

            if (resultSetTo.next()){
                accountTo = resultSetTo.getInt("account_amount");
            } else {
                throw new DAOException("Account with account_id " + toAccount.getId() + " not found");
            }

            resultTo = accountTo + payAmount;

            fromAccountStatement = proxyConnectionFrom.prepareStatement(DatabaseQueryConstant.UPDATE_ACCOUNT);
            fromAccountStatement.setDouble(1, resultFrom);
            fromAccountStatement.setInt(2, fromAccount.getId());
            fromAccountStatement.executeUpdate();

            fromAccountStatement = proxyConnectionTo.prepareStatement(DatabaseQueryConstant.UPDATE_ACCOUNT);
            fromAccountStatement.setDouble(1, resultTo);
            fromAccountStatement.setInt(2, toAccount.getId());
            fromAccountStatement.executeUpdate();

            proxyConnectionFrom.commit();
            proxyConnectionTo.commit();
        } catch (SQLException e) {
            try {
                proxyConnectionFrom.rollback();
                proxyConnectionTo.rollback();
            } catch (SQLException e1) {
                throw new DAOException("Rollback exception in DAO", e);
            }
            throw new DAOException("TransactionDAO exception in DAO", e);
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
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        Account account = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.FIND_ACCOUNT_BY_ID_FROM_ACCOUNT);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account = new Account();
                account.setId(resultSet.getInt("account_id"));
                account.setAccountAmount(resultSet.getDouble("account_amount"));
            }
        } catch (SQLException e) {
            throw new DAOException("Find account exception: ", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return account;
    }

    @Override
    public boolean delete(Account entity) throws DAOException {
        throw new UnsupportedOperationException("This method is prohibited");
    }

    @Override
    public boolean create(Account account) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean state = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.CREATE_NEW_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
            state = preparedStatement.executeUpdate() != 0;
            if (state) {
                setGeneratedId(account, preparedStatement);
            }
        } catch (SQLException e) {
            throw new DAOException("Create account exception", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return state;
    }

    private void setGeneratedId(Account account, PreparedStatement preparedStatement) throws SQLException, DAOException {
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                account.setId(generatedKeys.getInt(1));
            } else {
                throw new DAOException("Creating user failed, no ID obtained.");
            }
        }
    }

    @Override
    public boolean update(Account entity) throws DAOException {
        throw new UnsupportedOperationException("This method is prohibited");
    }
}
