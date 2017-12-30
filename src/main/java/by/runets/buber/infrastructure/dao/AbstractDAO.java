package by.runets.buber.infrastructure.dao;

import by.runets.buber.domain.entity.Entity;
import by.runets.buber.infrastructure.exception.ConnectionException;
import by.runets.buber.infrastructure.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface AbstractDAO<K, T extends Entity> {
    Logger LOGGER = LogManager.getLogger(AbstractDAO.class);

    List<T> findAll() throws DAOException;
    T find(K id) throws DAOException;
    void delete(T entity) throws DAOException;
    void create(T entity) throws DAOException, ConnectionException;
    void update(T entity) throws DAOException;

    default void close(Statement st){
        if (st != null){
            try {
                st.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    };
}
