package by.runets.buber.infrastructure.dao;

import by.runets.buber.domain.entity.Ban;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.exception.DAOException;

import java.util.List;

public interface UserDAO extends AbstractDAO<Integer, User> {
    boolean isEmailExist(String email) throws DAOException;
    User checkEmailPassword(String email, String password) throws DAOException;
    boolean setBanToUser(User user) throws DAOException;
    List<User> readBannedUsers() throws DAOException;
    int changePassword(String newPassword, int id) throws DAOException;
    String findPassword(int id) throws DAOException;
}
