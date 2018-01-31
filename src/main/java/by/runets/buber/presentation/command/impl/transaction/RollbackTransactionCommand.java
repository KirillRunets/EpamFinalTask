package by.runets.buber.presentation.command.impl.transaction;

import by.runets.buber.application.service.transaction.RollbackTransactionService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.LabelParameter;
import by.runets.buber.infrastructure.constant.PropertyKey;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.infrastructure.util.LocaleFileManager;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.routing.Route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RollbackTransactionCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(RollbackTransactionCommand.class);
    private RollbackTransactionService rollbackTransactionService;

    public RollbackTransactionCommand(RollbackTransactionService rollbackTransactionService) {
        this.rollbackTransactionService = rollbackTransactionService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        String page = null;

        String transactionId = req.getParameter(RequestParameter.TRANSACTION_ID);

        LOGGER.debug(transactionId);

        if (RequestValidator.getInstance().isValidateOrderData(transactionId)){
            try {
                rollbackTransactionService.rollbackTransaction(new Integer(transactionId));
                page = JspPagePath.ADMIN_HOME_PAGE;
            } catch (ServiceException e) {
                LOGGER.error(e);
            }
        } else {
            page = JspPagePath.TRANSACTION_PAGE + "?" + LabelParameter.ERROR_LABEL + "=" + true;
        }


        router.setRouteType(Router.RouteType.REDIRECT);
        router.setPagePath(page);

        return router;
    }
}
