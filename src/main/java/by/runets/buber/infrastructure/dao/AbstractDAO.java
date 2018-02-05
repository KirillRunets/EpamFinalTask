package by.runets.buber.infrastructure.dao;

import by.runets.buber.domain.entity.Entity;
import by.runets.buber.infrastructure.connection.ConnectionPool;
import by.runets.buber.infrastructure.connection.ProxyConnection;
import by.runets.buber.infrastructure.exception.ConnectionException;
import by.runets.buber.infrastructure.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Provides methods to CRUD table data.
 */
public interface AbstractDAO<K, T extends Entity> {
    Logger LOGGER = LogManager.getLogger(AbstractDAO.class);

    List<T> findAll() throws DAOException;
    T find(K id) throws DAOException;
    boolean delete(T entity) throws DAOException;
    boolean create(T entity) throws DAOException;
    boolean update(T entity) throws DAOException;

    default void close(Statement st, ProxyConnection proxyConnection){
        if (st != null){
            try {
                st.close();
                if (proxyConnection != null){
                    ConnectionPool.getInstance().releaseConnection(proxyConnection);
                }
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    };
}
