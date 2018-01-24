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

@WebFilter(urlPatterns = { "/jsp/driver/*" }, servletNames = {"controller"})
public class CheckNewOrderFilter implements Filter {
    private final static Logger LOGGER = LogManager.getLogger(CheckNewOrderFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false);

        if (session != null) {
            User user = (User) session.getAttribute(UserRoleType.USER);
            if (user != null){
                if (user.getRole().getRoleName().equalsIgnoreCase(UserRoleType.DRIVER)){
                    OrderExistService orderExistService = new OrderExistService();
                    try {
                        Order order = orderExistService.isExistOrderForDriver(user);
                        if (order != null){
                            req.getSession().setAttribute(RequestParameter.NEW_ORDER, order);
                        }
                    } catch (ServiceException e) {
                        LOGGER.error(e);
                    }
                }
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

/* switch (user.getRole().getRoleName().toUpperCase()){
                    case UserRoleType.ADMIN:
                        break;
                    case UserRoleType.DRIVER:


                        break;
                    case UserRoleType.PASSENGER:
                        req.getRequestDispatcher(JspPagePath.PASSENGER_HOME_PAGE).forward(req, res);
                        break;
                }*/
