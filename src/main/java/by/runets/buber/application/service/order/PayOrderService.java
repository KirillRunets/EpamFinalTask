package by.runets.buber.application.service.order;

import by.runets.buber.domain.entity.Account;
import by.runets.buber.domain.entity.Order;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.AccountTransactionDAO;
import by.runets.buber.infrastructure.dao.OrderDAO;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

import java.util.Date;

public class PayOrderService {
    public void payOrder(User fromUser, User driver, Double amount, Order order) throws ServiceException {
        Account from = fromUser.getAccount();
        Account to = null;
        try {
            AccountTransactionDAO accountTransactionDAO = (AccountTransactionDAO) DAOFactory.getInstance().createDAO(DAOType.ACCOUNT_DAO_TYPE);
            UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
            OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().createDAO(DAOType.ORDER_DAO_TYPE);
            order.setPaid(true);
            if (from != null && driver != null && amount != null){
                to = userDAO.findAccountByUserId(driver.getId());
                orderDAO.setOrderIsPaid(order.getId());
                accountTransactionDAO.payOrderTransaction(from, to, amount);
                accountTransactionDAO.commitToTransactionStory(from, to, amount, new Date());
            }
        } catch (DAOException e) {
            order.setPaid(false);
            throw new ServiceException("Pay order service exception");
        }
    }
}
