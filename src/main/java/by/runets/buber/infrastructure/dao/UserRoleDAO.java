package by.runets.buber.infrastructure.dao;

import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.exception.DAOException;

public interface UserRoleDAO extends AbstractDAO<Integer, User> {
    boolean createUserRoleCommunication(User user) throws DAOException;
    boolean updateUserRoleCommunication(User user) throws DAOException;
    boolean deleteUserRoleCommunication(User user) throws DAOException;
}
