package by.runets.buber.presentation.command.impl.order;

import by.runets.buber.application.service.order.RevokeOrderService;
import by.runets.buber.domain.entity.Order;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class provides method to implement revoke order command from driver or passenger.
 */
public class RevokeOrderCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(RevokeOrderCommand.class);
    private RevokeOrderService revokeOrderService;

    public RevokeOrderCommand(RevokeOrderService revokeOrderService) {
        this.revokeOrderService = revokeOrderService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        String page = null;
        User user = (User) req.getSession().getAttribute(UserRoleType.USER);
        Order order = (Order) req.getSession().getAttribute(RequestParameter.NEW_ORDER);
        boolean state = false;

        try {
            state = revokeOrderService.revoke(user.getRole().getRoleName(), user.getId(), order.getId());
            if (state){
                if (user.getRole().getRoleName().equalsIgnoreCase(UserRoleType.PASSENGER)){
                    req.getSession().removeAttribute(RequestParameter.NEW_ORDER);
                    page = JspPagePath.PASSENGER_HOME_PAGE;
                } else {
                    order.setDriver(new User());
                    req.getSession().setAttribute(RequestParameter.NEW_ORDER, order);
                    page = JspPagePath.DRIVER_HOME_PAGE;
                }
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        router.setRouteType(Router.RouteType.REDIRECT);
        router.setPagePath(page);

        return router;
    }
}
