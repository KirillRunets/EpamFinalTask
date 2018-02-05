package by.runets.buber.infrastructure.dao.impl;

import by.runets.buber.domain.entity.Bonus;
import by.runets.buber.infrastructure.connection.ConnectionPool;
import by.runets.buber.infrastructure.connection.ProxyConnection;
import by.runets.buber.infrastructure.constant.DatabaseQueryConstant;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Provide methods to work with bonus table.
 */
public class BonusDAOImpl implements AbstractDAO<Integer, Bonus> {
    private static BonusDAOImpl instance;

    private BonusDAOImpl() {
    }

    public static BonusDAOImpl getInstance() {
        if (instance == null) {
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
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.FIND_ALL_BONUSES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bonuses.add(getBonusFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Find all bonuses DAO exception", e);
        } finally {
           close(preparedStatement, proxyConnection);
        }
        return bonuses;
    }


    @Override
    public Bonus find(Integer id) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        Bonus bonus = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.FIND_BONUS_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                bonus = getBonusFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Find bonus exception: ", e);
        } finally {
            close(preparedStatement, proxyConnection);
        }
        return bonus;
    }

    @Override
    public boolean delete(Bonus bonus) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean state = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.DELETE_BONUS_BY_ID);
            preparedStatement.setInt(1, bonus.getId());
            state = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DAOException("Delete bonus exception ", e);
        } finally {
           close(preparedStatement, proxyConnection);
        }
        return state;
    }

    @Override
    public boolean create(Bonus bonus) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean state = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.INSERT_INTO_BONUS,   Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(bonus.getBonusType()));
            preparedStatement.setString(2, String.valueOf(bonus.getBonusDescription()));
            state = preparedStatement.executeUpdate() != 0;
            if (state){
                setGeneratedId(bonus, preparedStatement);
            }
        } catch (SQLException e) {
            throw new DAOException("Insertion exception", e);
        } finally {
           close(preparedStatement, proxyConnection);
        }
        return state;
    }

    private void setGeneratedId(Bonus bonus, PreparedStatement preparedStatement) throws SQLException, DAOException {
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                bonus.setId(generatedKeys.getInt(1));
            } else {
                throw new DAOException("Creating bonus failed, no ID obtained.");
            }
        }
    }

    @Override
    public boolean update(Bonus bonus) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean state = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.UPDATE_BONUS_BY_ID);
            preparedStatement.setString(1, bonus.getBonusType());
            preparedStatement.setString(2, bonus.getBonusDescription());
            preparedStatement.setInt(3, bonus.getId());
            state = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DAOException("Update bonus DAO exception", e);
        } finally {
           close(preparedStatement, proxyConnection);
        }
        return state;
    }

    private Bonus getBonusFromResultSet(ResultSet resultSet) throws SQLException {
        Bonus bonus = new Bonus();
        bonus.setId(resultSet.getInt("bonus_id"));
        bonus.setBonusType(resultSet.getString("bonus_name"));
        bonus.setBonusDescription(resultSet.getString("bonus_description"));
        return bonus;
    }
}
