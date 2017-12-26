package by.runets.buber.infrastructure.dao.impl;

import by.runets.buber.domain.entity.Ban;
import by.runets.buber.domain.entity.Bonus;
import by.runets.buber.domain.entity.Role;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.connection.ConnectionPool;
import by.runets.buber.infrastructure.connection.ProxyConnection;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.UserRoleDAO;
import by.runets.buber.infrastructure.dao.constant.RequestConstant;
import by.runets.buber.infrastructure.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserRoleDAO {
    @Override
    public List<User> findAll() throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        List<User> users = new ArrayList<>();
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.FIND_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                users.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Selection users exception " + e);
        } finally {
            close(preparedStatement);
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
        }
        return users;
    }

    @Override
    public User find(Integer id) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        User user = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.FIND_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user = getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Find user exception: " + e);
        } finally {
            close(preparedStatement);
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
        }
        return user;
    }

    @Override
    public void delete(User user) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.DELETE_USER_BY_ID);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Delete user exception " + e);
        } finally {
            close(preparedStatement);
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
        }
    }

    @Override
    public void create(User user) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.INSERT_INTO_USER);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getSecondName());
            preparedStatement.setDate(4, (java.sql.Date) new Date(user.getBirthDate().getTime()));
            preparedStatement.setInt(5, user.getBan().get().getId());
            preparedStatement.setDate(6, new java.sql.Date(user.getUnBaneDate().getTime()));
            preparedStatement.setString(7, user.getPhoneNumber());
            preparedStatement.setDouble(8, user.getRating());
            preparedStatement.setInt(9, user.getBonus().get().getId());
            preparedStatement.setInt(10, user.getTripAmount());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Insertion exception" + e);
        } finally {
            close(preparedStatement);
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
        }
    }

    @Override
    public void update(User user) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.UPDATE_USER_BY_ID);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getSecondName());
            preparedStatement.setDate(4, (java.sql.Date) new Date(user.getBirthDate().getTime()));
            preparedStatement.setInt(5, user.getBan().get().getId());
            preparedStatement.setDate(6, new java.sql.Date(user.getUnBaneDate().getTime()));
            preparedStatement.setString(7, user.getPhoneNumber());
            preparedStatement.setDouble(8, user.getRating());
            preparedStatement.setInt(9, user.getBonus().get().getId());
            preparedStatement.setInt(10, user.getTripAmount());
            preparedStatement.setInt(11, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Insertion exception" + e);
        } finally {
            close(preparedStatement);
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setEmail(resultSet.getString("email"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setSecondName(resultSet.getString("second_name"));
        user.setBirthDate(resultSet.getDate("birth_date"));
        user.setBan(java.util.Optional.of(new Ban(resultSet.getInt("ban"))));
        user.setUnBaneDate(resultSet.getDate("unban_date"));
        user.setPhoneNumber(resultSet.getString("phone_number"));
        user.setRating(resultSet.getDouble("rating"));
        user.setBonus(java.util.Optional.of(new Bonus(resultSet.getInt("bonus"))));
        user.setTripAmount(resultSet.getInt("trip_amount"));
        user.setRole(Optional.of(new Role(resultSet.getInt("r_id"), Optional.ofNullable(resultSet.getString("r_name")))));
        return user;
    }

    @Override
    public void createUserRoleCommunication(User user) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.INSERT_INTO_USER_M2M_ROLE);
            preparedStatement.setInt(1, user.getRole().get().getId());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("INSERT user role communication exception " + e);
        } finally {
            close(preparedStatement);
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
        }
    }

    @Override
    public void updateUserRoleCommunication(User user) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.UPDATE_USER_M2M_ROLE);
            preparedStatement.setInt(1, user.getRole().get().getId());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("UPDATE user role communication exception " + e);
        } finally {
            close(preparedStatement);
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
        }
    }

    @Override
    public void deleteUserRoleCommunication(User user) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.DELETE_USER_M2M_ROLE);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DELETE user role communication exception " + e);
        } finally {
            close(preparedStatement);
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
        }
    }
}
