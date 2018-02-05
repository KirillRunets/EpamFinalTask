package by.runets.buber.infrastructure.dao.impl;

import by.runets.buber.domain.entity.Account;
import by.runets.buber.domain.entity.Transaction;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.connection.ConnectionPool;
import by.runets.buber.infrastructure.connection.ProxyConnection;
import by.runets.buber.infrastructure.constant.DatabaseQueryConstant;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.TransactionDAO;
import by.runets.buber.infrastructure.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Provide methods to work with transaction table.
 */
public class TransactionDAOImpl implements TransactionDAO {
    private static TransactionDAOImpl instance;

    private TransactionDAOImpl() {
    }

    public static TransactionDAOImpl getInstance() {
        if (instance == null) {
            instance = new TransactionDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Transaction> findAll() throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        List<Transaction> transactions = new LinkedList<>();
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.FIND_ALL_TRANSACTION);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                transactions.add(getTransactionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Find all transactions exception", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return transactions;
    }

    @Override
    public Transaction find(Integer id) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        Transaction transaction = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.ROLLBACK_TRANSACTION);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                transaction = getTransactionFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Find order exception: ", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return transaction;
    }

    private Transaction getTransactionFromResultSet(ResultSet resultSet) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(resultSet.getInt("transaction_id"));
        transaction.setFrom(new Account(resultSet.getInt("from_account")));
        transaction.setTo(new Account(resultSet.getInt("to_account")));
        transaction.setDate(resultSet.getTimestamp("transaction_date"));
        transaction.setAmount(resultSet.getDouble("amount"));
        return transaction;
    }

    @Override
    public boolean delete(Transaction entity) throws DAOException {
        return false;
    }

    @Override
    public boolean create(Transaction transaction) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean isCreated = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.COMMIT_TO_TRANSACTION_STORY);
            preparedStatement.setInt(1, transaction.getFrom().getId());
            preparedStatement.setInt(2, transaction.getTo().getId());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.setTimestamp(4, new java.sql.Timestamp(transaction.getDate().getTime()));
            isCreated = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DAOException("Create transaction to transaction story exception", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return isCreated;
    }

    @Override
    public boolean update(Transaction transaction) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean isUpdated = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.COMMIT_TO_TRANSACTION_STORY);
            preparedStatement.setDouble(1, transaction.getAmount());
            preparedStatement.setTimestamp(2, new java.sql.Timestamp(transaction.getDate().getTime()));
            preparedStatement.setInt(3, transaction.getId());
            isUpdated = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DAOException("Update transaction to transaction story exception", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return isUpdated;
    }

    @Override
    public void commitToTransactionStory(Transaction transaction) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.COMMIT_TO_TRANSACTION_STORY);
            preparedStatement.setInt(1, transaction.getFrom().getId());
            preparedStatement.setInt(2, transaction.getTo().getId());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.setTimestamp(4, new java.sql.Timestamp(transaction.getDate().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Commit transaction to transaction story exception", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
    }
}
