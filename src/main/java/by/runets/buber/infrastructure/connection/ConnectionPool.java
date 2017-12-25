package by.runets.buber.infrastructure.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static AtomicBoolean instanceCreated = new AtomicBoolean();
    private static ReentrantLock lock = new ReentrantLock();
    private static ConnectionPool instance;
    private BlockingQueue<ProxyConnection> connectionBlockingQueue;

    public ConnectionPool() throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        initialize();
    }

    private void initialize() {
        try {
            DBManager dbResourceManager = DBManager.getInstance();

            dbResourceManager.initialize();

            String url = dbResourceManager.getValue(DBSettings.DB_URL);
            String username = dbResourceManager.getValue(DBSettings.DB_USER);
            String password = dbResourceManager.getValue(DBSettings.DB_PASSWORD);

            int poolSize = Integer.parseInt(dbResourceManager.getValue(DBSettings.DB_POOL_SIZE));

            connectionBlockingQueue = new ArrayBlockingQueue<>(poolSize);
            connectionBlockingQueue.forEach((i -> {
                try {
                    Connection connection = (ProxyConnection) DriverManager.getConnection(url, username, password);
                    ProxyConnection proxyConnection = new ProxyConnection(connection);
                    connectionBlockingQueue.add(proxyConnection);
                } catch (SQLException e) {
                    LOGGER.error("Create database connection error", e);
                }
            }));

        } catch (MissingResourceException e) {
            LOGGER.fatal("Database connection error");
            throw new RuntimeException("Database connection error", e);
        }

    }

    public static ConnectionPool getInstance() throws SQLException {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    instanceCreated.getAndSet(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = connectionBlockingQueue.take();
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection) {
        connectionBlockingQueue.offer(connection);
    }

    public void closePool() throws SQLException {
        for (ProxyConnection connection : connectionBlockingQueue) {
            connection.closeConnection();
        }
    }
}
