package by.runets.buber.application.filter;

import by.runets.buber.application.service.user.UpdateUserService;
import by.runets.buber.domain.entity.Order;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.LabelParameter;
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

@WebFilter(urlPatterns = { "/jsp/passenger/passenger_home.jsp"}, servletNames = {"controller"})
public class PassengerFilter implements Filter {
    private final static Logger LOGGER = LogManager.getLogger(PassengerFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();

        if (session != null){
            Order order = (Order) session.getAttribute(RequestParameter.NEW_ORDER);
            User user = (User) session.getAttribute(UserRoleType.USER);
            if (order != null){
                if (order.isCompleted()){
                    updateUserInDbAndSession(user, order, req);
                    res.sendRedirect(JspPagePath.PASSENGER_HOME_PAGE);
                }else if (order.getConfirmed()){
                    res.sendRedirect(JspPagePath.FREE_DRIVERS_FOR_PASSENGER_PAGE);
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }

    private void updateUserInDbAndSession(User user, Order order, HttpServletRequest req){
        UpdateUserService updateUserService = new UpdateUserService();
        try {
            user.getOrderSet().add(order);
            user.setTripAmount(user.getOrderSet().size());
            updateUserService.update(user);
            req.getSession().setAttribute(UserRoleType.USER, user);
        } catch (ServiceException e) {
            LOGGER.debug(e);
        }
    }
}
