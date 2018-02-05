package by.runets.buber.presentation.command.impl.transaction;

import by.runets.buber.application.service.transaction.ReadTransactionService;
import by.runets.buber.domain.entity.Transaction;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.LabelParameter;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.command.impl.order.ShowOrderCommand;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This class provides method to implement show all transactions command to admin.
 */
public class ShowTransactionCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(ShowOrderCommand.class);
    private ReadTransactionService readTransactionService;

    public ShowTransactionCommand(ReadTransactionService readTransactionService) {
        this.readTransactionService = readTransactionService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        String page = null;

        try {
            List<Transaction> transactions = readTransactionService.read();
            req.getSession().setAttribute(LabelParameter.TRANSACTION_LIST, transactions);
            page = JspPagePath.TRANSACTION_PAGE;
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        router.setRouteType(Router.RouteType.REDIRECT);
        router.setPagePath(page);

        return router;
    }
}
