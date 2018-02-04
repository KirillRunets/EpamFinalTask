package by.runets.buber.presentation.command.impl.bonus;

import by.runets.buber.application.service.bonus.SetBonusService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.Bonus;
import by.runets.buber.domain.entity.User;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class provides method to implement set bonus command to user from admin.
 */
public class SetBonusCommand implements Command{
    private final static Logger LOGGER = LogManager.getLogger(DeleteBonusCommand.class);
    private SetBonusService setBonusService;

    public SetBonusCommand(SetBonusService setBonusService) {
        this.setBonusService = setBonusService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = null;
        User user = null;

        try {
            router = ((user = init(req)) != null)
                    ? setBonusService.setBonusToUser(user)
                    ? rightRoute(user) : errorRoute(req) : errorRoute(req);
        } catch (ServiceException e){
            LOGGER.error(e);
        }

        return router;
    }

    /**
     *
     * @param user
     * @return router value with route type and page path
     */
    private Router rightRoute(User user) {
        Router router = new Router();

        router.setPagePath(JspPagePath.PASSENGER_ALL_INFO_PAGE);
        router.setRouteType(Router.RouteType.REDIRECT);

        return router;
    }

    /**
     *
     * @param req
     * @return router value with route type and page path and also set error notification in request
     * @throws ServiceException
     */
    private Router errorRoute(HttpServletRequest req) throws ServiceException {
        Router router = new Router();

        String locale = req.getSession().getAttribute(RequestParameter.LOCALE) == null ? RequestParameter.DEFAULT_LOCALE : req.getSession().getAttribute(RequestParameter.LOCALE).toString();
        req.setAttribute(LabelParameter.ERROR_LABEL, LocaleFileManager.getLocale(locale).getProperty(PropertyKey.ERROR_LABEL_MESSAGE));

        router.setPagePath(JspPagePath.SET_BONUS_FORM_PAGE);
        router.setRouteType(Router.RouteType.REDIRECT);

        return router;
    }

    /**
     *
     * @param req
     * @return user from request.
     */
    private User init(HttpServletRequest req) {
        User user = null;

        Integer userId = (Integer) req.getSession(false).getAttribute(RequestParameter.USER_ID);
        String bonusId = req.getParameter(RequestParameter.BONUS_ID);

        if (RequestValidator.getInstance().isValidateBonusData(bonusId)){
            user = new User();
            user.setId(userId);
            user.setBonus(new Bonus(new Integer(bonusId)));
        }

        return user;
    }
}
