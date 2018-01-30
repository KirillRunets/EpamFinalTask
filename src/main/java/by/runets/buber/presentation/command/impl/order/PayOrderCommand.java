package by.runets.buber.presentation.command.impl.order;

import by.runets.buber.application.service.order.PayOrderService;
import by.runets.buber.application.service.user.UpdateUserService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.Account;
import by.runets.buber.domain.entity.Order;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.LabelParameter;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PayOrderCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(PayOrderCommand.class);
    private PayOrderService payOrderService;

    public PayOrderCommand(PayOrderService payOrderService) {
        this.payOrderService = payOrderService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        String page = null;

        Order order = (Order) req.getSession().getAttribute(RequestParameter.NEW_ORDER);

        User fromUser = (User) req.getSession().getAttribute(UserRoleType.USER);
        User toUser = order.getDriver();

        Double amount = order.getTripCost();
        try {
            if (RequestValidator.getInstance().isValidatePayData(amount)){
                payOrderService.payOrder(fromUser, toUser, amount, order);
                page = JspPagePath.PASSENGER_HOME_PAGE;
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.REDIRECT);

        return router;
    }


}
