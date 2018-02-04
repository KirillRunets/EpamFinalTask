package by.runets.buber.presentation.command.impl.bonus;

import by.runets.buber.application.service.bonus.ReadBonusService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.Ban;
import by.runets.buber.domain.entity.Bonus;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.LabelParameter;
import by.runets.buber.infrastructure.constant.PropertyKey;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.infrastructure.util.LocaleFileManager;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.command.impl.ban.FillBanFormCommand;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FillBonusFormCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(FillBonusFormCommand.class);
    private ReadBonusService readBonusService;

    public FillBonusFormCommand(ReadBonusService readBonusService) {
        this.readBonusService = readBonusService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        List<Bonus> bonusList = null;
        String userId = req.getParameter(RequestParameter.USER_ID);
        try {
            router = RequestValidator.getInstance().isValidate(userId)
                    ? ((bonusList = readBonusService.readAll()) != null)
                    ? rightRoute(req, bonusList, new Integer(userId))
                    : errorRoute(req) : errorRoute(req);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        return router;
    }

    private Router rightRoute(HttpServletRequest req, List<Bonus> bonusList, Integer userId) {
        Router router = new Router();

        req.getSession().setAttribute(RequestParameter.USER_ID, userId);
        req.getSession().setAttribute(LabelParameter.BONUS_LIST, bonusList);

        router.setPagePath(JspPagePath.SET_BONUS_FORM_PAGE);
        router.setRouteType(Router.RouteType.REDIRECT);


        return router;
    }

    private Router errorRoute(HttpServletRequest req) {
        Router router = new Router();

        String locale = req.getSession().getAttribute(RequestParameter.LOCALE) == null ? RequestParameter.DEFAULT_LOCALE : req.getSession().getAttribute(RequestParameter.LOCALE).toString();
        req.setAttribute(LabelParameter.ERROR_LABEL, LocaleFileManager.getLocale(locale).getProperty(PropertyKey.ERROR_LABEL_MESSAGE));

        router.setPagePath(JspPagePath.PASSENGER_ALL_INFO_PAGE);
        router.setRouteType(Router.RouteType.FORWARD);

        return router;
    }
}
