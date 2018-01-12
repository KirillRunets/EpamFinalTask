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

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.INSERT_INTO_ORDER);
            preparedStatement.setDouble(1, order.getDistance());
            preparedStatement.setDouble(2, order.getTripCost());
            preparedStatement.setString(3, order.getStartPoint().toString());
            preparedStatement.setString(4, order.getDestinationPoint().toString());
            preparedStatement.setDate(5, new Date(order.getOrderDate().getTime()));
            preparedStatement.setInt(6, order.getDriver().get().getId());
            preparedStatement.setInt(7, order.getPassenger().get().getId());
            preparedStatement.executeUpdate();
            state = true;
        } catch (SQLException e) {
            throw new DAOException("Insertion exception" + e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
            close(preparedStatement);
        }
        return state;
    }

    @Override
    public void update(Order order) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.UPDATE_ORDER_BY_ID);
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
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
            close(preparedStatement);
        }
    }

    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        List<Double> coordinates = null;

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

    private Order getOrderFromResultSet(ResultSet resultSet, String role) throws SQLException {
        Order order = new Order();
        List<Double> coordinates = null;

        order.setId(resultSet.getInt("t_id"));
        order.setDistance(resultSet.getDouble("distance"));
        order.setTripCost(resultSet.getDouble("trip_cost"));
        coordinates = LocationParser.parseLocation(resultSet.getString("departure_point"));
        order.setStartPoint(Optional.of(new Point(coordinates.get(0), coordinates.get(1))));
        coordinates = LocationParser.parseLocation(resultSet.getString("destination_point"));
        order.setDestinationPoint(Optional.of(new Point(coordinates.get(0), coordinates.get(1))));
        order.setOrderDate(resultSet.getDate("date"));
        switch (role) {
            case UserRoleType.DRIVER:
                int id = resultSet.getInt("passenger_id");
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("first_name");
                String secondName = resultSet.getString("second_name");
                Date birthDate = resultSet.getDate("birth_date");
                int tripAmount = resultSet.getInt("trip_amount");
                String phoneNumber = resultSet.getString("phone_number");
                int rating = resultSet.getInt("rating");
                order.setPassenger(Optional.of(new User(id, email, firstName, secondName, birthDate, rating, tripAmount, phoneNumber)));
                break;
            case UserRoleType.PASSENGER:
                id = resultSet.getInt("driver_id");
                email = resultSet.getString("email");
                firstName = resultSet.getString("first_name");
                secondName = resultSet.getString("second_name");
                birthDate = resultSet.getDate("birth_date");
                tripAmount = resultSet.getInt("trip_amount");
                phoneNumber = resultSet.getString("phone_number");
                rating = resultSet.getInt("rating");
                order.setDriver(Optional.of(new User(id, email, firstName, secondName, birthDate, rating, tripAmount, phoneNumber)));
                break;
        }

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
                orders.add(getOrderFromResultSet(resultSet, UserRoleType.DRIVER));
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
                orders.add(getOrderFromResultSet(resultSet, UserRoleType.PASSENGER));
            }
        } catch (SQLException e) {
            throw new DAOException("Find order list by id exception: " + e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(proxyConnection);
            close(preparedStatement);
        }
        return orders;
    }
}
