package by.runets.buber.infrastructure.dao.impl;

import by.runets.buber.domain.entity.Ban;
import by.runets.buber.infrastructure.connection.ConnectionPool;
import by.runets.buber.infrastructure.connection.ProxyConnection;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.constant.DatabaseQueryConstant;
import by.runets.buber.infrastructure.exception.ConnectionException;
import by.runets.buber.infrastructure.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BanDAOImpl implements AbstractDAO<Integer, Ban> {
    private static BanDAOImpl instance;

    private BanDAOImpl() {
    }

    public static BanDAOImpl getInstance() {
        if (instance == null) {
            instance = new BanDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Ban> findAll() throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        List<Ban> bans = new ArrayList<>();
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.FIND_ALL_BANS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bans.add(getBanFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Selection bans exception ", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
            close(preparedStatement);
        }
        return bans;
    }

    @Override
    public Ban find(Integer id) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        Ban ban = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.FIND_BAN_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ban = getBanFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Find ban exception: ", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
            close(preparedStatement);
        }
        return ban;
    }

    @Override
    public boolean delete(Ban ban) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean state = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.DELETE_BAN_BY_ID);
            preparedStatement.setInt(1, ban.getId());
            state = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DAOException("Delete ban exception ", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
            close(preparedStatement);
        }
        return state;
    }

    @Override
    public boolean create(Ban ban) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean state = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.INSERT_INTO_BAN);
            preparedStatement.setString(1, String.valueOf(ban.getBanType()));
            preparedStatement.setString(2, String.valueOf(ban.getBanDescription()));
            state = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DAOException("Insertion exception", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
            close(preparedStatement);
        }
        return state;
    }

    @Override
    public boolean update(Ban ban) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean state = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.UPDATE_BAN_BY_ID);
            preparedStatement.setString(1, String.valueOf(ban.getBanType()));
            preparedStatement.setString(2, String.valueOf(ban.getBanDescription()));
            preparedStatement.setInt(7, ban.getId());
            state = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DAOException("Insertion exception", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
            close(preparedStatement);
        }
        return state;
    }

    private Ban getBanFromResultSet(ResultSet resultSet) throws SQLException {
        Ban ban = new Ban();
        ban.setId(resultSet.getInt("b_id"));
        ban.setBanType(resultSet.getString("b_name"));
        ban.setBanDescription(resultSet.getString("b_description"));
        return ban;
    }
}
