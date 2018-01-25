package by.runets.buber.application.filter;


import by.runets.buber.application.service.order.OrderExistService;
import by.runets.buber.domain.entity.Order;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = { "/jsp/*" }, servletNames = {"controller"})
public class OrderFilter implements Filter {
    private final static Logger LOGGER = LogManager.getLogger(OrderFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false);
        Order order = null;
        if (session != null) {
            User user = (User) session.getAttribute(UserRoleType.USER);
            if (user != null){
                if ((order = checkIsExistNewOrder(user)) != null){
                    req.getSession().setAttribute(RequestParameter.NEW_ORDER, order);
                } else {
                    if (req.getSession().getAttribute(RequestParameter.NEW_ORDER) != null){
                        req.getSession().removeAttribute(RequestParameter.NEW_ORDER);
                    }
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private Order checkIsExistNewOrder(User user){
        OrderExistService orderExistService = new OrderExistService();
        Order order = null;
        try {
            order = orderExistService.isExistOrderForDriver(user);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }
        return order;
    }
}