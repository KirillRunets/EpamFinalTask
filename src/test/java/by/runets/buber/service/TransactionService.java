package by.runets.buber.service;

import by.runets.buber.application.service.transaction.RollbackTransactionService;
import by.runets.buber.infrastructure.exception.ServiceException;
import org.testng.annotations.Test;

public class TransactionService {
    @Test
    public void testRollbackTransaction() throws ServiceException {
        RollbackTransactionService rollbackTransactionService = new RollbackTransactionService();
        rollbackTransactionService.rollbackTransaction(22);
    }
}
