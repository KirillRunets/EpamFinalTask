package by.runets.buber.presentation.command.impl.order;

import by.runets.buber.application.service.order.ConfirmOrderService;
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

public class ConfirmOrderCommand implements Command{
    private final static Logger LOGGER = LogManager.getLogger(ConfirmOrderCommand.class);
    private ConfirmOrderService confirmOrderService;

    public ConfirmOrderCommand(ConfirmOrderService confirmOrderService) {
        this.confirmOrderService = confirmOrderService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        String page = null;
        Order order = (Order) req.getSession().getAttribute(RequestParameter.NEW_ORDER);
        User user = (User) req.getSession().getAttribute(UserRoleType.USER);
        boolean state = false;
        try {
            order.setConfirmed(true);
            state = confirmOrderService.confirm(order);
            if (state){
                req.getSession().setAttribute(RequestParameter.NEW_ORDER, order);
                page = JspPagePath.DRIVER_HOME_PAGE;
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        router.setRouteType(Router.RouteType.REDIRECT);
        router.setPagePath(page);

        return router;
    }
}
