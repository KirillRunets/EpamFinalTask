package by.runets.buber.dao;

import by.runets.buber.domain.entity.Ban;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.util.NumberFormatLocaleFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BanDAOTest {
    @Test
    public void testFindAll() throws DAOException {
        AbstractDAO abstractDAO = DAOFactory.getInstance().createDAO(DAOType.BAN_DAO_TYPE);
        Assert.assertNotNull(abstractDAO.findAll());
    }

    @Test
    public void testUnbanUser() throws DAOException {
        UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
        userDAO.setBanToUser(new User(3));
        Assert.assertEquals(null, userDAO.readBannedUsers());
    }

    @Test
    public void testSetBan() throws DAOException, ParseException {
        String numberFormatPattern = NumberFormatLocaleFactory.factory("ru_RU");
        UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
        User user = new User(3, new Ban(8), new SimpleDateFormat(numberFormatPattern).parse("2018-08-01"));
        userDAO.setBanToUser(user);
        Assert.assertNotNull(userDAO.readBannedUsers());
    }

    @Test
    public void testCreateBan() throws DAOException {
        AbstractDAO abstractDAO = DAOFactory.getInstance().createDAO(DAOType.BAN_DAO_TYPE);
        Ban ban = new Ban();
        ban.setBanType("Test");
        ban.setBanDescription("Test");
        abstractDAO.create(ban);

        Assert.assertNotEquals(0, ban.getId());
    }
}
