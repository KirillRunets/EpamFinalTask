package by.runets.buber.infrastructure.dao.impl;

import by.runets.buber.domain.entity.Car;
import by.runets.buber.domain.entity.Point;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.connection.ConnectionPool;
import by.runets.buber.infrastructure.connection.ProxyConnection;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.constant.DatabaseQueryConstant;
import by.runets.buber.infrastructure.dao.parser.LocationParser;
import by.runets.buber.infrastructure.exception.ConnectionException;
import by.runets.buber.infrastructure.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDAOImpl implements AbstractDAO<Integer, Car> {
    private static CarDAOImpl instance;

    private CarDAOImpl() {
    }

    public static CarDAOImpl getInstance() {
        if (instance == null) {
            instance = new CarDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Car> findAll() throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        List<Car> cars = new ArrayList<>();
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.FIND_ALL_CARS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cars.add(getCarFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Selection cars exception ", e);
        } finally {
           close(preparedStatement, proxyConnection);
        }
        return cars;
    }

    @Override
    public Car find(Integer id) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        Car car = null;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.FIND_CAR_BY_OWNER);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                car = getCarFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Find car exception: ", e);
        } finally {
           close(preparedStatement, proxyConnection);
        }
        return car;
    }

    @Override
    public boolean delete(Car car) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean state = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.DELETE_CAR_BY_ID);
            preparedStatement.setInt(1, car.getId());
            state = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DAOException("Delete car exception ", e);
        } finally {
           close(preparedStatement, proxyConnection);
        }
        return false;
    }

    @Override
    public boolean create(Car car) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean state = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.INSERT_INTO_CAR);
            setInsertPrepareStatement(preparedStatement, car);
            state = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DAOException("Insertion exception", e);
        } finally {
           close(preparedStatement, proxyConnection);
        }
        return state;
    }

    private void setInsertPrepareStatement(PreparedStatement preparedStatement, Car car) throws SQLException {
        preparedStatement.setString(1, car.getMark());
        preparedStatement.setString(2, car.getModel());
        preparedStatement.setDate(3, new Date(car.getReleaseDate().getTime()));
        if (car.getLicensePlate() != null) {
            preparedStatement.setString(4, car.getLicensePlate());
        } else {
            preparedStatement.setNull(4, Types.INTEGER);
        }
        preparedStatement.setInt(5, car.getCarOwner().getId());
        if (car.getCurrentLocation() != null) {
            preparedStatement.setString(6, car.getCurrentLocation().toString());
        } else {
            preparedStatement.setNull(6, Types.INTEGER);
        }
    }

    private void setUpdatePrepareStatement(PreparedStatement preparedStatement, Car car) throws SQLException {
        preparedStatement.setString(1, String.valueOf(car.getMark()));
        preparedStatement.setString(2, String.valueOf(car.getModel()));
        if (car.getReleaseDate() == null) {
            preparedStatement.setNull(3, Types.INTEGER);
        } else {
            preparedStatement.setDate(3, new Date(car.getReleaseDate().getTime()));
        }
        if (car.getLicensePlate() != null) {
            preparedStatement.setString(4, car.getLicensePlate());
        } else {
            preparedStatement.setNull(4, Types.INTEGER);
        }
        if (car.getCarOwner() == null) {
            preparedStatement.setNull(5, Types.INTEGER);
        } else {
            preparedStatement.setInt(5, car.getCarOwner().getId());
        }
        preparedStatement.setInt(5, car.getCarOwner().getId());
        if (car.getCurrentLocation() == null) {
            preparedStatement.setString(6, new Point().toString());
        } else {
            preparedStatement.setString(6, car.getCurrentLocation().toString());
        }
        preparedStatement.setInt(7, car.getId());
    }

    @Override
    public boolean update(Car car) throws DAOException {
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean state = false;
        try {
            preparedStatement = proxyConnection.prepareStatement(DatabaseQueryConstant.UPDATE_CAR_BY_ID);
            setUpdatePrepareStatement(preparedStatement, car);
            state = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new DAOException("Insertion exception", e);
        } finally {
           close(preparedStatement, proxyConnection);
        }
        return state;
    }

    private Car getCarFromResultSet(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        List<Double> coordinates = null;
        car.setId(resultSet.getInt("id"));
        car.setMark(resultSet.getString("mark"));
        car.setModel(resultSet.getString("model"));
        car.setReleaseDate(resultSet.getDate("release_date"));
        car.setLicensePlate(resultSet.getString("license_plate"));
        car.setCarOwner(new User(resultSet.getInt("car_owner")));
        coordinates = LocationParser.parseLocation(resultSet.getString("current_location"));
        if (coordinates != null && !coordinates.isEmpty()) {
            car.setCurrentLocation(new Point(coordinates.get(0), coordinates.get(1)));
        } else {
            car.setCurrentLocation(new Point());
        }
        return car;
    }
}
