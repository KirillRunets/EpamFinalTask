package by.runets.buber.presentation.command.impl.bonus;

import by.runets.buber.application.service.bonus.CreateBonusService;
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
 * This class provides method to implement create bonus command from admin.
 */
public class CreateBonusCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(CreateBonusCommand.class);
    private CreateBonusService createBonusService;

    public CreateBonusCommand(CreateBonusService createBonusService) {
        this.createBonusService = createBonusService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = null;
        Bonus bonus = null;
        try {
            router = (bonus = init(req)) != null
                    ? createBonusService.create(bonus)
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
     * @return right router value with route type and page path and also create bonus in session.
     */
    private Router rightRoute(HttpServletRequest req, Bonus bonus) {
        Router router = new Router();

        createBonusInSession(req, bonus);

        router.setPagePath(JspPagePath.BONUS_PAGE);
        router.setRouteType(Router.RouteType.REDIRECT);

        return router;
    }

    /**
     *
     * @param req
     * @return error router value with route type and page path
     */
    private Router errorRoute(HttpServletRequest req) {
        Router router = new Router();

        String locale = req.getSession().getAttribute(RequestParameter.LOCALE) == null ? RequestParameter.DEFAULT_LOCALE : req.getSession().getAttribute(RequestParameter.LOCALE).toString();
        req.setAttribute(LabelParameter.ERROR_LABEL, LocaleFileManager.getLocale(locale).getProperty(PropertyKey.ERROR_LABEL_MESSAGE));

        router.setRouteType(Router.RouteType.FORWARD);
        router.setPagePath(JspPagePath.BONUS_PAGE);

        return router;
    }

    /**
     * This method creates bonus object in user session.
     * @param req
     * @param bonus
     */
    private void createBonusInSession(HttpServletRequest req, Bonus bonus){
        List<Bonus> bonusList = (List<Bonus>) req.getSession().getAttribute(LabelParameter.BONUS_LIST);
        bonusList.add(bonus);
        req.getSession().setAttribute(LabelParameter.BONUS_LIST, bonusList);
    }

    /**
     * This method get request values and init bonus object if they are valid else null.
     * @param req
     * @return bonus object.
     */
    private Bonus init(HttpServletRequest req){
        String bonusType = req.getParameter(RequestParameter.BONUS_TYPE);
        String bonusDescription = req.getParameter(RequestParameter.BONUS_DESCRIPTION);
        Bonus bonus = null;

        if (RequestValidator.getInstance().isValidate(bonusType) && RequestValidator.getInstance().isValidate(bonusDescription)){
            bonus = new Bonus();
            bonus.setBonusType(bonusType);
            bonus.setBonusDescription(bonusDescription);
        }

        return bonus;
    }
}
