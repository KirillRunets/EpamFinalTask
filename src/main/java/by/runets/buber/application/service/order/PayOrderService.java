package by.runets.buber.application.service.order;

import by.runets.buber.domain.entity.Account;
import by.runets.buber.domain.entity.Order;
import by.runets.buber.domain.entity.Transaction;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.AccountDAO;
import by.runets.buber.infrastructure.dao.OrderDAO;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.dao.impl.TransactionDAOImpl;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

import java.util.Date;

public class PayOrderService {
    public void payOrder(User fromUser, User driver, Double amount, Order order) throws ServiceException {
        Account from = fromUser.getAccount();
        Account to = null;
        try {
            AccountDAO accountDAO = (AccountDAO) DAOFactory.getInstance().createDAO(DAOType.ACCOUNT_DAO_TYPE);
            UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
            OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().createDAO(DAOType.ORDER_DAO_TYPE);
            TransactionDAOImpl transactionDAO = (TransactionDAOImpl) DAOFactory.getInstance().createDAO(DAOType.ACCOUNT_DAO_TYPE);
            order.setPaid(true);
            if (from != null && driver != null && amount != null){
                to = userDAO.findAccountByUserId(driver.getId());

                orderDAO.setOrderIsPaid(order.getId());
                accountDAO.payOrderTransaction(from, to, amount);
                transactionDAO.commitToTransactionStory(new Transaction(from, to, new Date(), amount));
            }
        } catch (DAOException e) {
            order.setPaid(false);
            throw new ServiceException("Pay order service exception");
        }
    }
}
