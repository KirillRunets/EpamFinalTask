package by.runets.buber.presentation.command.impl.bonus;

import by.runets.buber.application.service.bonus.ReadBonusService;
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
 * This class provides method to implement read bonus command from admin.
 */
public class ReadBonusCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(DeleteBonusCommand.class);
    private ReadBonusService readBonusService;

    public ReadBonusCommand(ReadBonusService readBonusService) {
        this.readBonusService = readBonusService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        List<Bonus> bonusList = null;

        try {
            router = ((bonusList = readBonusService.readAll()) != null)
                    ? rightRoute(req, bonusList)
                    : errorRoute(req);
        } catch (ServiceException e){
            LOGGER.error(e);
        }

        return router;
    }

    /**
     *
     * @param req
     * @param bonusList
     * @return right router value with route type and page path and also set bonusList to session.
     */
    private Router rightRoute(HttpServletRequest req, List<Bonus> bonusList){
        Router router = new Router();
        req.getSession().setAttribute(LabelParameter.BONUS_LIST, bonusList);

        router.setRouteType(Router.RouteType.REDIRECT);
        router.setPagePath(JspPagePath.BONUS_PAGE);

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

        return router;
    }
}
