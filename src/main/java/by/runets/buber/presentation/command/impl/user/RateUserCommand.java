package by.runets.buber.presentation.command.impl.user;

import by.runets.buber.application.service.statistic.RatingStatisticService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.Order;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.LabelParameter;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RateUserCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(RateUserCommand.class);
    private RatingStatisticService ratingStatisticService;

    public RateUserCommand(RatingStatisticService ratingStatisticService) {
        this.ratingStatisticService = ratingStatisticService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        String page = null;

        User sessionUser = (User) req.getSession().getAttribute(UserRoleType.USER);
        String ratingFromAnotherUser = req.getParameter(RequestParameter.RATING);
        boolean isDriver = sessionUser.getRole().getRoleName().equalsIgnoreCase(UserRoleType.DRIVER);

        Order order = init(sessionUser);
        User user = order.getPassenger();

        if (RequestValidator.getInstance().isValidate(ratingFromAnotherUser)){
            try {
                ratingStatisticService.calculateAverageRating(new Double(ratingFromAnotherUser), user);
                req.getSession().removeAttribute(LabelParameter.COMPLETED);
                page = isDriver ? JspPagePath.DRIVER_HOME_PAGE : JspPagePath.PASSENGER_HOME_PAGE;
            } catch (ServiceException e) {
                LOGGER.error(e);
            }
        }

        router.setRouteType(Router.RouteType.REDIRECT);
        router.setPagePath(page);

        return router;
    }

    private Order init(User sessionUser){
        return sessionUser.getOrderSet()
                .stream()
                .reduce((first, second) -> second)
                .orElse(null);
    }
}
