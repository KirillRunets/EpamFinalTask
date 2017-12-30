package by.runets.buber.infrastructure.dao;

import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.exception.DAOException;

public interface UserDAO extends AbstractDAO<Integer, User> {
    boolean isEmailExist(String email) throws DAOException;
    User checkEmailPassword(String email, String password) throws DAOException;
}
