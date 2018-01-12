package by.runets.buber.infrastructure.connection;

import by.runets.buber.infrastructure.constant.PropertyKey;
import by.runets.buber.infrastructure.constant.PropertyPath;
import by.runets.buber.infrastructure.exception.ConnectionException;
import by.runets.buber.infrastructure.exception.IOFileException;
import by.runets.buber.infrastructure.util.PropertyFileManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
    private int poolSize;

    private ConnectionPool(){
        initialize();
    }

    private void initialize() {
        try {
            PropertyFileManager propertyFileManager = new PropertyFileManager(PropertyPath.CONFIG_PROPERTIES_FILE);

            String url = propertyFileManager.getValue(PropertyKey.DB_URL);
            String username = propertyFileManager.getValue(PropertyKey.DB_USER);
            String password = propertyFileManager.getValue(PropertyKey.DB_PASSWORD);

            poolSize= Integer.parseInt(propertyFileManager.getValue(PropertyKey.DB_POOL_SIZE));
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connectionBlockingQueue = new ArrayBlockingQueue<>(poolSize);

            for (int i = 0; i < poolSize; i++){
                Connection connection = DriverManager.getConnection(url, username, password);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                connectionBlockingQueue.put(proxyConnection);
            }
        } catch (SQLException e) {
            LOGGER.fatal("Driver connection error");
            throw new RuntimeException("Driver connection error", e);
        } catch (IOFileException | InterruptedException e) {
            LOGGER.error(e);
        }
    }

    @Override
    protected void finalize() throws ConnectionException {
        closePool();
    }

    public static ConnectionPool getInstance(){
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

    public ProxyConnection getConnection()  {
        ProxyConnection connection = null;
        try {
            if (!connectionBlockingQueue.isEmpty()){
                connection = connectionBlockingQueue.take();
            }
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection) {
        lock.lock();
        if (!connectionBlockingQueue.contains(connection)) {
            connectionBlockingQueue.offer(connection);
        }
        lock.unlock();
    }

    private void closePool() throws ConnectionException {
        for (ProxyConnection connection : connectionBlockingQueue) {
            try {
                connection.closeConnection();
            } catch (SQLException e) {
                throw new ConnectionException("Close pool exception" + e);
            }
        }
    }

    public int getPoolSize() {
        return poolSize;
    }
}
