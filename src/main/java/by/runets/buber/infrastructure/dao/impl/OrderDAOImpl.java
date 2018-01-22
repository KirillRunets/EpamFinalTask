package by.runets.buber.infrastructure.dao.impl;

import by.runets.buber.domain.entity.Order;
import by.runets.buber.domain.entity.Point;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.connection.ConnectionPool;
import by.runets.buber.infrastructure.connection.ProxyConnection;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.constant.DatabaseQueryConstant;
import by.runets.buber.infrastructure.dao.OrderDAO;
import by.runets.buber.infrastructure.dao.parser.LocationParser;
import by.runets.buber.infrastructure.exception.ConnectionException;
import by.runets.buber.infrastructure.exception.DAOException;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class OrderDAOImpl implements OrderDAO {
    private static OrderDAOImpl instance;

    private OrderDAOImpl() {
    }

    public static OrderDAOImpl getInstance() {
        if (instance == null) {
            instance = new OrderDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Order> findAll() throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        List<Order> orders = new ArrayList<>();
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.FIND_ALL_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(getOrderFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Selection orders exception " + e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
            close(preparedStatement);
        }
        return orders;
    }

    @Override
    public Order find(Integer id) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        Order order = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.FIND_ORDER_BY_PASSENGER_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = getOrderFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Find order exception: " + e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
            close(preparedStatement);
        }
        return order;
    }

    @Override
    public void delete(Order order) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.DELETE_ORDER_BY_ID);
            preparedStatement.setInt(1, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Delete order exception " + e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
            close(preparedStatement);
        }
    }

    @Override
    public boolean create(Order order) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean state = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.INSERT_INTO_ORDER, Statement.RETURN_GENERATED_KEYS);
            setOrderInsertPrepareStatement(order, preparedStatement);
            state = preparedStatement.executeUpdate() != 0;
            if (state){
                setGeneratedId(order, preparedStatement);
            }
        } catch (SQLException e) {
            throw new DAOException("Insertion exception" + e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
            close(preparedStatement);
        }
        return state;
    }

    private void setOrderInsertPrepareStatement(Order order, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setDouble(1, order.getDistance());
        preparedStatement.setDouble(2, order.getTripCost());
        preparedStatement.setString(3, order.getStartPoint().toString());
        preparedStatement.setString(4, order.getDestinationPoint().toString());
        preparedStatement.setDate(5, new Date(order.getOrderDate().getTime()));
        preparedStatement.setInt(6, order.getDriverId());
        preparedStatement.setInt(7, order.getPassengerId());
        preparedStatement.setDouble(8, order.getTripTime());
    }

    @Override
    public void update(Order order) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.UPDATE_ORDER_BY_ID);
            setOrderUpdatePrepareStatement(order, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Insertion exception" + e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
            close(preparedStatement);
        }
    }

    private void setOrderUpdatePrepareStatement(Order order, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setDouble(1, order.getDistance());
        preparedStatement.setDouble(2, order.getTripCost());
        preparedStatement.setString(3, order.getStartPoint().toString());
        preparedStatement.setString(4, order.getDestinationPoint().toString());
        preparedStatement.setDate(5, new Date(order.getOrderDate().getTime()));
        preparedStatement.setInt(6, order.getDriverId());
        preparedStatement.setInt(7, order.getPassengerId());
        preparedStatement.setDouble(8, order.getTripTime());
        preparedStatement.setInt(9, order.getId());
    }

    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        List<Double> coordinates = null;

        order.setId(resultSet.getInt("t_id"));
        order.setDistance(resultSet.getDouble("distance"));
        order.setTripCost(resultSet.getDouble("trip_cost"));
        coordinates = LocationParser.parseLocation(resultSet.getString("departure_point"));
        order.setStartPoint(new Point(coordinates.get(0), coordinates.get(1)));
        coordinates = LocationParser.parseLocation(resultSet.getString("destination_point"));
        order.setDestinationPoint(new Point(coordinates.get(0), coordinates.get(1)));
        order.setOrderDate(resultSet.getDate("date"));
        order.setDriverId(resultSet.getInt("driver_id"));
        order.setPassengerId(resultSet.getInt("passenger_id"));

        return order;
    }

    @Override
    public Set<Order> findAllOrdersByDriverId(Integer id) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        Set<Order> orders = new LinkedHashSet<>();
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.FIND_ORDER_BY_DRIVER_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(getOrderFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Find order list by id exception: " + e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
            close(preparedStatement);
        }
        return orders;
    }

    @Override
    public Set<Order> findAllOrdersByPassengerId(Integer id) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        Set<Order> orders = new LinkedHashSet<>();
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.FIND_ORDER_BY_PASSENGER_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(getOrderFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Find order list by id exception: " + e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
            close(preparedStatement);
        }
        return orders;
    }

    private void setGeneratedId(Order order, PreparedStatement preparedStatement) throws SQLException, DAOException {
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getInt(1));
            } else {
                throw new DAOException("Creating order failed, no ID obtained.");
            }
        }
    }

    @Override
    public boolean confirmOrderByDriver(Order order) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean isUpdated = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.CONFIRM_ORDER_BY_DRIVER);
            preparedStatement.setBoolean(1, order.isConfirmed());
            preparedStatement.setInt(2, order.getId());
            isUpdated = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DAOException("Confirm order exception exception" + e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
            close(preparedStatement);
        }
        return isUpdated;
    }
}
