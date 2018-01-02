package by.runets.buber.dao;

import by.runets.buber.domain.entity.Role;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.ConnectionException;
import by.runets.buber.infrastructure.exception.DAOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserDAOTest {
    @Test
    public void testCheckEmailPassword() throws DAOException {
        String email = "rkmwork97@gmail.com";
        String password = "e3789937c929732baae6e891d947dcf6";
        UserDAO dao = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
        Assert.assertNotNull(dao.checkEmailPassword(email, password));
    }

    @Test
    public void testIsEmailExist() throws DAOException {
        String email = "rkmwork97@gmail.com";
        UserDAO dao = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
        Assert.assertEquals(true, dao.isEmailExist(email));
    }

    @Test
    public void testFindAll() throws DAOException {
        UserDAO dao = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
        Assert.assertNotNull(dao.findAll());
    }

}
