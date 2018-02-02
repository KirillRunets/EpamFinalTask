package by.runets.buber.presentation.command.impl.ban;

import by.runets.buber.application.service.ban.DeleteBanService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.Ban;
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

public class DeleteBanCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(DeleteBanCommand.class);
    private DeleteBanService deleteBanService;

    public DeleteBanCommand(DeleteBanService deleteBanService) {
        this.deleteBanService = deleteBanService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();

        String banId = req.getParameter(RequestParameter.BAN_ID);
        try {
            router = RequestValidator.getInstance().isValidate(banId)
                    ? deleteBanService.delete(new Ban(new Integer(banId)))
                    ? rightRoute(req, new Integer(banId)) : errorRoute(req) : errorRoute(req);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        return router;
    }

    private Router rightRoute(HttpServletRequest req, Integer banId) {
        Router router = new Router();

        deleteBanInSession(req, banId);

        router.setPagePath(JspPagePath.BAN_PAGE);
        router.setRouteType(Router.RouteType.REDIRECT);
        return router;
    }

    private Router errorRoute(HttpServletRequest req) {
        Router router = new Router();

        String locale = req.getSession().getAttribute(RequestParameter.LOCALE) == null ? RequestParameter.DEFAULT_LOCALE : req.getSession().getAttribute(RequestParameter.LOCALE).toString();
        req.setAttribute(LabelParameter.ERROR_LABEL, LocaleFileManager.getLocale(locale).getProperty(PropertyKey.ERROR_LABEL_MESSAGE));

        router.setRouteType(Router.RouteType.FORWARD);
        router.setPagePath(JspPagePath.BAN_PAGE);

        return router;
    }

    private void deleteBanInSession(HttpServletRequest req, Integer banId){
        List<Ban> banList = (List<Ban>) req.getSession().getAttribute(LabelParameter.BAN_LIST);
        banList.removeIf(ban -> ban.getId() == banId);
        req.getSession().setAttribute(LabelParameter.BAN_LIST, banList);
    }
}
