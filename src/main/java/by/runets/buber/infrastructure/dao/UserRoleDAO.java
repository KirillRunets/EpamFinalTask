package by.runets.buber.infrastructure.dao;

import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.exception.DAOException;

public interface UserRoleDAO extends AbstractDAO<Integer, User> {
    void createUserRoleCommunication(User user) throws DAOException;
    void updateUserRoleCommunication(User user) throws DAOException;
    void deleteUserRoleCommunication(User user) throws DAOException;
}
