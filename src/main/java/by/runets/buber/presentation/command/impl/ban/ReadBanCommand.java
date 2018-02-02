package by.runets.buber.presentation.command.impl.ban;

import by.runets.buber.application.service.ban.ReadBanService;
import by.runets.buber.domain.entity.Ban;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.*;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.infrastructure.util.LocaleFileManager;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ReadBanCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(ReadBanCommand.class);
    private ReadBanService readBanService;

    public ReadBanCommand(ReadBanService readBanService) {
        this.readBanService = readBanService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        List<Ban> banList = null;

        try {
            router = ((banList = readBanService.find()) != null) ? rightRoute(req, banList) : errorRoute(req);
        } catch (ServiceException e){
            LOGGER.error(e);
        }

        return router;
    }

    private Router rightRoute(HttpServletRequest req, List<Ban> banList){
        Router router = new Router();
        req.getSession().setAttribute(LabelParameter.BAN_LIST, banList);

        router.setRouteType(Router.RouteType.REDIRECT);
        router.setPagePath(JspPagePath.BAN_PAGE);

        return router;
    }

    private Router errorRoute(HttpServletRequest req) {
        Router router = new Router();

        String locale = req.getSession().getAttribute(RequestParameter.LOCALE) == null ? RequestParameter.DEFAULT_LOCALE : req.getSession().getAttribute(RequestParameter.LOCALE).toString();
        req.setAttribute(LabelParameter.ERROR_LABEL, LocaleFileManager.getLocale(locale).getProperty(PropertyKey.ERROR_LABEL_MESSAGE));

        router.setPagePath(JspPagePath.BAN_FORM_PAGE);

        return router;
    }
}
