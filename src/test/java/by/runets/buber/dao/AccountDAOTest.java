package by.runets.buber.dao;

import by.runets.buber.domain.entity.Account;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.AccountTransactionDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import org.testng.annotations.Test;

public class AccountDAOTest {
    @Test
    public void testPayOrder() throws DAOException {
        Account fromAccount = new Account(1);
        Account toAccount = new Account(2);
        Double amount = 100.0;

        AccountTransactionDAO accountTransactionDAO = (AccountTransactionDAO) DAOFactory.getInstance().createDAO(DAOType.ACCOUNT_DAO_TYPE);
        accountTransactionDAO.payOrderTransaction(fromAccount, toAccount, amount);
    }
}
