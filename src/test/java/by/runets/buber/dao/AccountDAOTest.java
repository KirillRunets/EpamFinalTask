package by.runets.buber.dao;

import by.runets.buber.domain.entity.Account;
import by.runets.buber.domain.entity.Transaction;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.AccountDAO;
import by.runets.buber.infrastructure.dao.TransactionDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

public class AccountDAOTest {
    @Test
    public void testPayOrder() throws DAOException {
        Account fromAccount = new Account(1);
        Account toAccount = new Account(2);
        Double amount = 100.0;
        AccountDAO accountDAO = (AccountDAO) DAOFactory.getInstance().createDAO(DAOType.ACCOUNT_DAO_TYPE);
        TransactionDAO transactionDAO = (TransactionDAO) DAOFactory.getInstance().createDAO(DAOType.TRANSACTION_DAO_TYPE);
        accountDAO.transfer(fromAccount, toAccount, amount);
        transactionDAO.commitToTransactionStory(new Transaction(fromAccount, toAccount, new Date(), amount));
    }

    @Test
    public void testFindAccount() throws DAOException {
        AccountDAO accountDAO = (AccountDAO) DAOFactory.getInstance().createDAO(DAOType.ACCOUNT_DAO_TYPE);
        System.out.printf(String.valueOf(accountDAO.find(23).getId()));
        System.out.printf(String.valueOf(accountDAO.find(23).getAccountAmount()));
        Assert.assertNotNull(accountDAO.find(23));
    }

    @Test
    public void testFindAllTransactions() throws DAOException {
        TransactionDAO transactionDAO = (TransactionDAO) DAOFactory.getInstance().createDAO(DAOType.TRANSACTION_DAO_TYPE);
        Assert.assertNotNull(transactionDAO.findAll());
    }
}
