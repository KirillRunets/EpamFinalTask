package by.runets.buber.presentation.command.impl.order;

import by.runets.buber.application.service.order.ReadOrderService;
import by.runets.buber.domain.entity.Order;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.LabelParameter;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.routing.Route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowOrderCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(ShowOrderCommand.class);
    private ReadOrderService readOrderService;

    public ShowOrderCommand(ReadOrderService readOrderService) {
        this.readOrderService = readOrderService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        String page = null;

        try {
            List<Order> orderList = readOrderService.readOrderList();
            req.getSession().setAttribute(LabelParameter.ORDER_LIST, orderList);
            page = JspPagePath.ADMIN_ORDER_LIST_PAGE;
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.REDIRECT);

        return router;
    }
}
