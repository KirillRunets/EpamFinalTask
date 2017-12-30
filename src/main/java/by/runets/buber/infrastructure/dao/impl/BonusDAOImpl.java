package by.runets.buber.infrastructure.dao.impl;

import by.runets.buber.domain.entity.Bonus;
import by.runets.buber.infrastructure.connection.ConnectionPool;
import by.runets.buber.infrastructure.connection.ProxyConnection;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.constant.RequestConstant;
import by.runets.buber.infrastructure.exception.ConnectionException;
import by.runets.buber.infrastructure.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BonusDAOImpl implements AbstractDAO<Integer, Bonus> {
    private static BonusDAOImpl instance;

    private BonusDAOImpl(){}

    public static BonusDAOImpl getInstance(){
        if (instance == null){
            instance = new BonusDAOImpl();
        }
        return instance;
    }
    @Override
    public List<Bonus> findAll() throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        List<Bonus> bonuses = new ArrayList<>();
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.FIND_ALL_BONUSES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                bonuses.add(getBonusFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Selection bonuses exception " + e);
        } finally {
            close(preparedStatement);
            try {
                ConnectionPool.getInstance().releaseConnection(proxyConnection);
            } catch (ConnectionException e) {
                LOGGER.error(e);
            }
        }
        return bonuses;
    }



    @Override
    public Bonus find(Integer id) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        Bonus bonus = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.FIND_BONUS_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                bonus = getBonusFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Find bonus exception: " + e);
        } finally {
            close(preparedStatement);
            try {
                ConnectionPool.getInstance().releaseConnection(proxyConnection);
            } catch (ConnectionException e) {
                LOGGER.error(e);
            }
        }
        return bonus;
    }

    @Override
    public void delete(Bonus bonus) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.DELETE_BONUS_BY_ID);
            preparedStatement.setInt(1, bonus.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Delete bonus exception " + e);
        } finally {
            close(preparedStatement);
            try {
                ConnectionPool.getInstance().releaseConnection(proxyConnection);
            } catch (ConnectionException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public void create(Bonus bonus) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.INSERT_INTO_BONUS);
            preparedStatement.setString(1, String.valueOf(bonus.getBonusType()));
            preparedStatement.setString(2, String.valueOf(bonus.getBonusDescription()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Insertion exception" + e);
        } finally {
            close(preparedStatement);
            try {
                ConnectionPool.getInstance().releaseConnection(proxyConnection);
            } catch (ConnectionException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public void update(Bonus bonus) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.UPDATE_BONUS_BY_ID);
            preparedStatement.setString(1, String.valueOf(bonus.getBonusType()));
            preparedStatement.setString(2, String.valueOf(bonus.getBonusDescription()));
            preparedStatement.setInt(7, bonus.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Update exception" + e);
        } finally {
            close(preparedStatement);
            try {
                ConnectionPool.getInstance().releaseConnection(proxyConnection);
            } catch (ConnectionException e) {
                LOGGER.error(e);
            }
        }
    }

    private Bonus getBonusFromResultSet(ResultSet resultSet) throws SQLException {
        Bonus bonus = new Bonus();
        bonus.setId(resultSet.getInt("bonus_id"));
        bonus.setBonusType(Optional.ofNullable(resultSet.getString("bonus_name")));
        bonus.setBonusDescription(Optional.ofNullable(resultSet.getString("bonus_description")));
        return bonus;
    }
}