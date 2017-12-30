package by.runets.buber.connection;

import by.runets.buber.infrastructure.connection.ConnectionPool;
import by.runets.buber.infrastructure.constant.PropertyPath;
import by.runets.buber.infrastructure.constant.PropertyKey;
import by.runets.buber.infrastructure.connection.ProxyConnection;
import by.runets.buber.infrastructure.exception.ConnectionException;
import by.runets.buber.infrastructure.exception.IOFileException;
import by.runets.buber.infrastructure.util.PropertyFileManager;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestConnectionPool {

    @Test
    public void testConnectionPool() throws InterruptedException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        ProxyConnection proxyConnection = connectionPool.getConnection();

        Assert.assertNotEquals(null, proxyConnection);
    }


    /*This test should throw ConnectionException*/
    @Test
    public void testReleaseConnection() throws ConnectionException, IOFileException {
        PropertyFileManager propertyFileManager = new PropertyFileManager(PropertyPath.CONFIG_FILE);


        int actual = Integer.parseInt(propertyFileManager.getValue(PropertyKey.DB_POOL_SIZE));
        ProxyConnection proxyConnection = ConnectionPool.getInstance().getConnection();
        ConnectionPool.getInstance().releaseConnection(proxyConnection);
        ConnectionPool.getInstance().releaseConnection(proxyConnection);
        Assert.assertEquals(actual, ConnectionPool.getInstance().getPoolSize());
    }

    @Test
    public void testNotEqualsConnection(){
        ProxyConnection firstProxyConnection = ConnectionPool.getInstance().getConnection();
        ProxyConnection secondProxyConnection = ConnectionPool.getInstance().getConnection();

        Assert.assertNotEquals(firstProxyConnection, secondProxyConnection);
    }

}
