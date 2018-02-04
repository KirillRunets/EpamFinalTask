package by.runets.buber.infrastructure.dao.impl;

import by.runets.buber.domain.entity.*;
import by.runets.buber.infrastructure.connection.ConnectionPool;
import by.runets.buber.infrastructure.connection.ProxyConnection;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.UserRoleDAO;
import by.runets.buber.infrastructure.constant.DatabaseQueryConstant;
import by.runets.buber.infrastructure.exception.ConnectionException;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.util.PasswordEncrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserRoleDAO, UserDAO {
    private static UserDAOImpl instance;

    private UserDAOImpl() {
    }

    public static UserDAOImpl getInstance() {
        if (instance == null) {
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
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.FIND_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Selection users exception ", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return users;
    }

    @Override
    public User find(Integer id) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        User user = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.FIND_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Find user exception: ", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return user;
    }

    @Override
    public boolean delete(User user) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean state = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.DELETE_USER_BY_ID);
            preparedStatement.setInt(1, user.getId());
            state = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DAOException("Delete user exception ", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return state;
    }

    @Override
    public boolean create(User user) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean state = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.INSERT_INTO_USER, Statement.RETURN_GENERATED_KEYS);
            setUserToInsertPS(user, preparedStatement);
            state = preparedStatement.executeUpdate() != 0;
            if (state) {
                setGeneratedId(user, preparedStatement);
            }
        } catch (SQLException e) {
            throw new DAOException("Create user exception", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return state;
    }

    private void setUserToUpdatePS(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setString(2, user.getFirstName());
        preparedStatement.setString(3, user.getSecondName());
        if (user.getBirthDate() != null) {
            preparedStatement.setDate(4, new java.sql.Date(user.getBirthDate().getTime()));
        } else {
            preparedStatement.setNull(4, Types.INTEGER);
        }
        if (user.getBan() != null && user.getBan().getId() != 0) {
            preparedStatement.setInt(5, user.getBan().getId());
        } else {
            preparedStatement.setNull(5, Types.INTEGER);
        }
        if (user.getUnBaneDate() != null) {
            preparedStatement.setDate(6, new java.sql.Date(user.getUnBaneDate().getTime()));
        } else {
            preparedStatement.setNull(6, Types.INTEGER);
        }
        preparedStatement.setString(7, user.getPhoneNumber());
        preparedStatement.setDouble(8, user.getRating());
        if (user.getBonus() != null && user.getBonus().getId() != 0) {
            preparedStatement.setInt(9, user.getBonus().getId());
        } else {
            preparedStatement.setNull(9, Types.INTEGER);
        }
        preparedStatement.setInt(10, user.getTripAmount());
        preparedStatement.setInt(11, user.getId());
    }

    private void setUserToInsertPS(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setString(2, PasswordEncrypt.encryptPassword(user.getPassword()));
        preparedStatement.setString(3, user.getFirstName());
        preparedStatement.setString(4, user.getSecondName());
        if (user.getBan() != null && user.getUnBaneDate() != null && user.getBirthDate() != null && user.getBonus() != null) {
            preparedStatement.setDate(5, new java.sql.Date(user.getBirthDate().getTime()));
            preparedStatement.setInt(6, user.getBan().getId());
            preparedStatement.setDate(7, new java.sql.Date(user.getUnBaneDate().getTime()));
            preparedStatement.setInt(10, user.getBonus().getId());
        } else {
            preparedStatement.setNull(5, Types.INTEGER);
            preparedStatement.setNull(6, Types.INTEGER);
            preparedStatement.setNull(7, Types.INTEGER);
            preparedStatement.setNull(10, Types.INTEGER);
        }
        preparedStatement.setString(8, user.getPhoneNumber());
        preparedStatement.setDouble(9, user.getRating());
        preparedStatement.setInt(11, user.getTripAmount());
        preparedStatement.setInt(12, user.getAccount().getId());
    }

    private void setGeneratedId(User user, PreparedStatement preparedStatement) throws SQLException, DAOException {
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            } else {
                throw new DAOException("Creating user failed, no ID obtained.");
            }
        }
    }

    @Override
    public boolean update(User user) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean state = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.UPDATE_USER_BY_ID);
            setUserToUpdatePS(user, preparedStatement);
            state = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DAOException("Update exception", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return state;
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
    public boolean createUserRoleCommunication(User user) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean state = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.INSERT_INTO_USER_M2M_ROLE);
            preparedStatement.setInt(1, user.getRole().getId());
            preparedStatement.setInt(2, user.getId());
            state = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DAOException("INSERT user role communication exception ", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return state;
    }

    @Override
    public boolean updateUserRoleCommunication(User user) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean state = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.UPDATE_USER_M2M_ROLE);
            preparedStatement.setInt(1, user.getRole().getId());
            preparedStatement.setInt(2, user.getId());
            state = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DAOException("UPDATE user role communication exception ", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return state;
    }

    @Override
    public boolean deleteUserRoleCommunication(User user) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean state = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.DELETE_USER_M2M_ROLE);
            preparedStatement.setInt(1, user.getId());
            state = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DAOException("DELETE user role communication exception ", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return state;
    }

    @Override
    public boolean isEmailExist(String email) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean state;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.FIND_EMAIL_EXIST);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            state = resultSet.next();
        } catch (SQLException e) {
            throw new DAOException("Check email exception: ", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return state;
    }

    @Override
    public User checkEmailPassword(String email, String password) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        User user = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.FIND_USER_BY_EMAIL_PASSWORD);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Find user exception: ", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return user;
    }

    @Override
    public boolean setBanToUser(User user) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean state = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.SET_BAN_TO_USER);
            setBanToPreparedStatement(user, preparedStatement);
            state = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DAOException("SET ban to user exception ", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return state;
    }

    @Override
    public boolean setBonusToUser(User user) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean state = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.SET_BONUS_TO_USER);
            preparedStatement.setInt(1, user.getBonus().getId());
            preparedStatement.setInt(2, user.getId());
            state = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DAOException("SET bonus to user exception ", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return state;
    }

    private void setBanToPreparedStatement(User user, PreparedStatement preparedStatement) throws SQLException {
        if (user.getBan() != null) {
            preparedStatement.setInt(1, user.getBan().getId());
            preparedStatement.setDate(2, new java.sql.Date(user.getUnBaneDate().getTime()));
        } else {
            preparedStatement.setNull(1, Types.INTEGER);
            preparedStatement.setNull(2, Types.INTEGER);
        }
        preparedStatement.setInt(3, user.getId());
    }

    @Override
    public List<User> readBannedUsers() throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        List<User> userList = new ArrayList<>();
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.FIND_BANNED_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userList.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Read banned users dao exception", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return userList;
    }

    @Override
    public int changePassword(String newPassword, int id) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        int countUpdateRow = 0;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.UPDATE_PASSWORD_BY_ID);
            preparedStatement.setString(1, PasswordEncrypt.encryptPassword(newPassword));
            preparedStatement.setInt(2, id);
            countUpdateRow = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Update password dao exception", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return countUpdateRow;
    }

    @Override
    public String findPassword(int id) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        String password = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.SELECT_USER_PASSWORD);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                password = resultSet.getString("password");
            }
        } catch (SQLException e) {
            throw new DAOException("Select password dao exception", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return password;
    }

    @Override
    public Account findAccountByUserId(Integer id) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        Account account = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.FIND_ACCOUNT_BY_USER_ID_FROM_USER);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account = new Account();
                account.setId(resultSet.getInt("account_id"));
            }
        } catch (SQLException e) {
            throw new DAOException("Find account exception: ", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return account;
    }

    @Override
    public boolean updateUserRating(Integer id, Double rating) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean isUpdated = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.UPDATE_USER_RATING);
            preparedStatement.setDouble(1, rating);
            preparedStatement.setInt(2, id);
            isUpdated = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DAOException("Update rating exception: ", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return isUpdated;
    }
}
