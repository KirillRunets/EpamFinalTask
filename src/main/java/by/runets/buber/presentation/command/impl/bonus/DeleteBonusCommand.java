package by.runets.buber.presentation.command.impl.bonus;

import by.runets.buber.application.service.bonus.DeleteBonusService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.Bonus;
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
import java.util.List;


/**
 * This class provides method to implement delete bonus command from admin.
 */
public class DeleteBonusCommand implements Command{
    private final static Logger LOGGER = LogManager.getLogger(DeleteBonusCommand.class);
    private DeleteBonusService deleteBonusService;

    public DeleteBonusCommand(DeleteBonusService deleteBonusService) {
        this.deleteBonusService = deleteBonusService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = null;

        String bonusId = req.getParameter(RequestParameter.BONUS_ID);
        try {
            router = RequestValidator.getInstance().isValidate(bonusId)
                    ? deleteBonusService.delete(new Bonus(new Integer(bonusId)))
                    ? rightRoute(req, new Integer(bonusId)) : errorRoute(req) : errorRoute(req);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        return router;
    }

    /**
     *
     * @param req
     * @param banId
     * @return right router value with route type and page path and also delete bonus object in session bonus list.
     */
    private Router rightRoute(HttpServletRequest req, Integer banId) {
        Router router = new Router();

        deleteBonusInSession(req, banId);

        router.setPagePath(JspPagePath.BONUS_PAGE);
        router.setRouteType(Router.RouteType.REDIRECT);
        return router;
    }

    /**
     *
     * @param req
     * @return error router value with route type and page path and also set error message to request.
     */
    private Router errorRoute(HttpServletRequest req) {
        Router router = new Router();

        String locale = req.getSession().getAttribute(RequestParameter.LOCALE) == null ? RequestParameter.DEFAULT_LOCALE : req.getSession().getAttribute(RequestParameter.LOCALE).toString();
        req.setAttribute(LabelParameter.ERROR_LABEL, LocaleFileManager.getLocale(locale).getProperty(PropertyKey.ERROR_LABEL_MESSAGE));

        router.setPagePath(JspPagePath.BONUS_PAGE);
        router.setRouteType(Router.RouteType.FORWARD);

        return router;
    }

    /**
     * Delete bonus in bonus session list.
     * @param req
     * @param bonusId
     */
    private void deleteBonusInSession(HttpServletRequest req, Integer bonusId){
        List<Bonus> bonusList = (List<Bonus>) req.getSession().getAttribute(LabelParameter.BONUS_LIST);
        bonusList.removeIf(bonus -> bonus.getId() == bonusId);
        req.getSession().setAttribute(LabelParameter.BONUS_LIST, bonusList);
    }
}
