package by.runets.buber.presentation.command.impl.order;

import by.runets.buber.application.service.order.CompleteOrderService;
import by.runets.buber.application.service.user.UpdateUserService;
import by.runets.buber.domain.entity.Order;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.*;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.infrastructure.util.LocaleFileManager;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CompleteOrderCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(CompleteOrderCommand.class);
    private CompleteOrderService completeOrderService;

    public CompleteOrderCommand(CompleteOrderService completeOrderService) {
        this.completeOrderService = completeOrderService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        String page = null;
        boolean state = false;

        Order order = (Order) req.getSession().getAttribute(RequestParameter.NEW_ORDER);
        User sessionUser = (User) req.getSession().getAttribute(UserRoleType.USER);

        try {
            state = completeOrderService.complete(order.getId());
            if (state){
                order.setCompleted(true);

                sessionUser.getOrderSet().add(order);
                sessionUser.setTripAmount(sessionUser.getOrderSet().size());

                req.getSession().setAttribute(UserRoleType.USER, sessionUser);
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
