package by.runets.buber.presentation.command.impl.bonus;

import by.runets.buber.application.service.bonus.UpdateBonusService;
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
 * This class provides method to implement update bonus command from admin.
 */
public class UpdateBonusCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(DeleteBonusCommand.class);
    private UpdateBonusService updateBonusService;

    public UpdateBonusCommand(UpdateBonusService updateBonusService) {
        this.updateBonusService = updateBonusService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        Bonus bonus = null;
        try{
            router = (bonus = init(req)) != null
                    ? updateBonusService.update(bonus)
                    ? rightRoute(req, bonus) : errorRoute(req) : errorRoute(req);
        } catch (ServiceException e){
            LOGGER.error(e);
        }

        return router;
    }

    /**
     *
     * @param req
     * @param bonus
     * @return right router value with route type and page path and also update bonus in session bonus list.
     */
    private Router rightRoute(HttpServletRequest req, Bonus bonus){
        Router router = new Router();

        updateBonusInSession(req, bonus);

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

        router.setPagePath(JspPagePath.BONUS_FORM);
        router.setRouteType(Router.RouteType.FORWARD);

        return router;
    }

    /**
     * Init bonus object from request values.
     * @param req
     * @return bonus object if request values are valid or null.
     */
    private Bonus init(HttpServletRequest req){
        Bonus bonus = null;

        String id = req.getParameter(RequestParameter.BONUS_ID);
        String type = req.getParameter(RequestParameter.BONUS_TYPE);
        String description = req.getParameter(RequestParameter.BONUS_DESCRIPTION);

        if (RequestValidator.getInstance().isValidate(id) && RequestValidator.getInstance().isValidate(type) && RequestValidator.getInstance().isValidate(description)){
            bonus = new Bonus();
            bonus.setId(new Integer(id));
            bonus.setBonusType(type);
            bonus.setBonusDescription(description);
        }

        return bonus;
    }

    /**
     * Update bonus list in session.
     * @param req
     * @param bonus
     */
    private void updateBonusInSession(HttpServletRequest req, Bonus bonus){
        List<Bonus> bonusList = (List<Bonus>) req.getSession().getAttribute(LabelParameter.BONUS_LIST);

        bonusList.removeIf(b -> b.getId() == bonus.getId());
        bonusList.add(bonus);

        req.getSession().setAttribute(LabelParameter.BONUS_LIST, bonusList);
    }
}
