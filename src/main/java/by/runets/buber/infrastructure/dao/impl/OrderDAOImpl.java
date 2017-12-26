package by.runets.buber.infrastructure.dao.impl;

import by.runets.buber.domain.entity.Order;
import by.runets.buber.domain.entity.Point;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.connection.ConnectionPool;
import by.runets.buber.infrastructure.connection.ProxyConnection;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.constant.RequestConstant;
import by.runets.buber.application.parser.LocationParser;
import by.runets.buber.infrastructure.exception.DAOException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAOImpl implements AbstractDAO<Integer, Order> {
    @Override
    public List<Order> findAll() throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        List<Order> orders = new ArrayList<>();
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.FIND_ALL_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                orders.add(getOrderFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Selection orders exception " + e);
        } finally {
            close(preparedStatement);
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
        }
        return orders;
    }

    @Override
    public Order find(Integer id) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        Order order = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.FIND_ORDER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                order = getOrderFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Find order exception: " + e);
        } finally {
            close(preparedStatement);
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
        }
        return order;
    }

    @Override
    public void delete(Order order) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.DELETE_ORDER_BY_ID);
            preparedStatement.setInt(1, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Delete order exception " + e);
        } finally {
            close(preparedStatement);
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
        }
    }

    @Override
    public void create(Order order) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.INSERT_INTO_ORDER);
            preparedStatement.setDouble(1, order.getDistance());
            preparedStatement.setDouble(2, order.getTripCost());
            preparedStatement.setString(3, order.getStartPoint().toString());
            preparedStatement.setString(4, order.getDestinationPoint().toString());
            preparedStatement.setDate(5, new Date(order.getOrderDate().getTime()));
            preparedStatement.setInt(6, order.getDriver().get().getId());
            preparedStatement.setInt(7, order.getPassenger().get().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Insertion exception" + e);
        } finally {
            close(preparedStatement);
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
        }
    }

    @Override
    public void update(Order order) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(RequestConstant.UPDATE_ORDER_BY_ID);
            preparedStatement.setDouble(1, order.getDistance());
            preparedStatement.setDouble(2, order.getTripCost());
            preparedStatement.setString(3, order.getStartPoint().toString());
            preparedStatement.setString(4, order.getDestinationPoint().toString());
            preparedStatement.setDate(5, new Date(order.getOrderDate().getTime()));
            preparedStatement.setInt(6, order.getDriver().get().getId());
            preparedStatement.setInt(7, order.getPassenger().get().getId());
            preparedStatement.setInt(8, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Insertion exception" + e);
        } finally {
            close(preparedStatement);
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
        }
    }

    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        List<Double> coordinates = new ArrayList<>();
        order.setId(resultSet.getInt("t_id"));
        order.setDistance(resultSet.getDouble("distance"));
        order.setTripCost(resultSet.getDouble("trip_cost"));
        coordinates = LocationParser.parseLocation(resultSet.getString("departure_point"));
        order.setStartPoint(Optional.of(new Point(coordinates.get(0), coordinates.get(1))));
        coordinates = LocationParser.parseLocation(resultSet.getString("destination_point"));
        order.setDestinationPoint(Optional.of(new Point(coordinates.get(0), coordinates.get(1))));
        order.setOrderDate(resultSet.getDate("date"));
        order.setDriver(Optional.of(new User(resultSet.getInt("driver_id"))));
        order.setPassenger(Optional.of(new User(resultSet.getInt("passenger_id"))));
        return order;
    }
}
