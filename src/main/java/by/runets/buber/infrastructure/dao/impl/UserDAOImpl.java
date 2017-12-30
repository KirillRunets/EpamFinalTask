package by.runets.buber.infrastructure.dao.impl;

import by.runets.buber.domain.entity.Ban;
import by.runets.buber.domain.entity.Bonus;
import by.runets.buber.domain.entity.Role;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.connection.ConnectionPool;
import by.runets.buber.infrastructure.connection.ProxyConnection;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.UserRoleDAO;
import by.runets.buber.infrastructure.constant.RequestConstant;
import by.runets.buber.infrastructure.exception.ConnectionException;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.util.PasswordEncrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserRoleDAO, UserDAO {
    private static UserDAOImpl instance;

    private UserDAOImpl(){}

    public static UserDAOImpl getInstance(){
        if (instance == null){
            instance = new UserDAOImpl();
        }
        return instance;
    }

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
            try {
                ConnectionPool.getInstance().releaseConnection(proxyConnection);
            } catch (ConnectionException e) {
                LOGGER.error(e);
            }
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
            try {
                ConnectionPool.getInstance().releaseConnection(proxyConnection);
            } catch (ConnectionException e) {
                LOGGER.error(e);
            }
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
            try {
                ConnectionPool.getInstance().releaseConnection(proxyConnection);
            } catch (ConnectionException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public void create(User user) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.INSERT_INTO_USER);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, PasswordEncrypt.encryptPassword(user.getPassword()));
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getSecondName());
            preparedStatement.setDate(5, new java.sql.Date(user.getBirthDate().getTime()));
            if (user.getBan() != null && user.getUnBaneDate() != null && user.getBonus() != null){
                preparedStatement.setInt(6, user.getBan().getId());
                preparedStatement.setDate(7, new java.sql.Date(user.getUnBaneDate().getTime()));
                preparedStatement.setInt(10, user.getBonus().getId());
            } else {
                preparedStatement.setNull(6, Types.INTEGER);
                preparedStatement.setNull(7, Types.INTEGER);
                preparedStatement.setNull(10, Types.INTEGER);
            }
            preparedStatement.setString(8, user.getPhoneNumber());
            preparedStatement.setDouble(9, user.getRating());
            preparedStatement.setInt(11, user.getTripAmount());
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
    public void update(User user) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.UPDATE_USER_BY_ID);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getSecondName());
            preparedStatement.setDate(4, new java.sql.Date(user.getBirthDate().getTime()));
            preparedStatement.setInt(5, user.getBan().getId());
            preparedStatement.setDate(6, new java.sql.Date(user.getUnBaneDate().getTime()));
            preparedStatement.setString(7, user.getPhoneNumber());
            preparedStatement.setDouble(8, user.getRating());
            preparedStatement.setInt(9, user.getBonus().getId());
            preparedStatement.setInt(10, user.getTripAmount());
            preparedStatement.setInt(11, user.getId());
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

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setEmail(resultSet.getString("email"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setSecondName(resultSet.getString("second_name"));
        user.setBirthDate(resultSet.getDate("birth_date"));
        user.setBan(new Ban(resultSet.getInt("ban")));
        user.setUnBaneDate(resultSet.getDate("unban_date"));
        user.setPhoneNumber(resultSet.getString("phone_number"));
        user.setRating(resultSet.getDouble("rating"));
        user.setBonus(new Bonus(resultSet.getInt("bonus")));
        user.setTripAmount(resultSet.getInt("trip_amount"));
        user.setRole(new Role(resultSet.getInt("r_id"), resultSet.getString("r_name")));
        return user;
    }

    @Override
    public void createUserRoleCommunication(User user) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.INSERT_INTO_USER_M2M_ROLE);
            preparedStatement.setInt(1, user.getRole().getId());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("INSERT user role communication exception " + e);
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
    public void updateUserRoleCommunication(User user) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.UPDATE_USER_M2M_ROLE);
            preparedStatement.setInt(1, user.getRole().getId());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("UPDATE user role communication exception " + e);
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
            try {
                ConnectionPool.getInstance().releaseConnection(proxyConnection);
            } catch (ConnectionException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public boolean isEmailExist(String email) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean state = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.FIND_EMAIL_EXIST);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                state = true;
            }
        } catch (SQLException e) {
            throw new DAOException("Find user exception: " + e);
        } finally {
            close(preparedStatement);
            try {
                ConnectionPool.getInstance().releaseConnection(proxyConnection);
            } catch (ConnectionException e) {
                LOGGER.error(e);
            }
        }
        return state ;
    }

    @Override
    public User checkEmailPassword(String email, String password) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        User user = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.FIND_USER_BY_EMAIL_PASSWORD);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user = getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Find user exception: " + e);
        } finally {
            close(preparedStatement);
            try {
                ConnectionPool.getInstance().releaseConnection(proxyConnection);
            } catch (ConnectionException e) {
                LOGGER.error(e);
            }
        }
        return user;
    }
}
