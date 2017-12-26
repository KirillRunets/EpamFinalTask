package by.runets.buber.infrastructure.dao.impl;

import by.runets.buber.domain.entity.Ban;
import by.runets.buber.infrastructure.connection.ConnectionPool;
import by.runets.buber.infrastructure.connection.ProxyConnection;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.constant.RequestConstant;
import by.runets.buber.infrastructure.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BanDAOImpl implements AbstractDAO<Integer, Ban> {
    @Override
    public List<Ban> findAll() throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        List<Ban> bans = new ArrayList<>();
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.FIND_ALL_BANS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                bans.add(getBanFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Selection bans exception " + e);
        } finally {
            close(preparedStatement);
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
        }
        return bans;
    }

    @Override
    public Ban find(Integer id) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        Ban ban = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.FIND_BAN_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                ban = getBanFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Find ban exception: " + e);
        } finally {
            close(preparedStatement);
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
        }
        return ban;
    }

    @Override
    public void delete(Ban ban) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.DELETE_BAN_BY_ID);
            preparedStatement.setInt(1, ban.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Delete ban exception " + e);
        } finally {
            close(preparedStatement);
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
        }
    }

    @Override
    public void create(Ban ban) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.INSERT_INTO_BAN);
            preparedStatement.setString(1, String.valueOf(ban.getBanType()));
            preparedStatement.setString(2, String.valueOf(ban.getBanDescription()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Insertion exception" + e);
        } finally {
            close(preparedStatement);
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
        }
    }

    @Override
    public void update(Ban ban) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.UPDATE_BAN_BY_ID);
            preparedStatement.setString(1, String.valueOf(ban.getBanType()));
            preparedStatement.setString(2, String.valueOf(ban.getBanDescription()));
            preparedStatement.setInt(7, ban.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Insertion exception" + e);
        } finally {
            close(preparedStatement);
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
        }
    }

    private Ban getBanFromResultSet(ResultSet resultSet) throws SQLException {
        Ban ban = new Ban();
        ban.setId(resultSet.getInt("b_id"));
        ban.setBanType(Optional.ofNullable(resultSet.getString("b_name")));
        ban.setBanDescription(Optional.ofNullable(resultSet.getString("b_description")));
        return ban;
    }
}
