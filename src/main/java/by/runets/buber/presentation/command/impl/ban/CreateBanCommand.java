package by.runets.buber.presentation.command.impl.ban;

import by.runets.buber.application.service.ban.CreateBanService;
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

/**
 * This class provides method to implement create ban by admin.
 */
public class CreateBanCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(CreateBanCommand.class);
    private CreateBanService createBanService;

    public CreateBanCommand(CreateBanService createBanService) {
        this.createBanService = createBanService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        Ban ban = null;
        try {
            router = (ban = init(req)) != null
                    ? createBanService.create(ban)
                    ? rightRoute(req, ban) : errorRoute(req) : errorRoute(req);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        return router;
    }

    /**
     *
     * @param req
     * @param ban
     * @return right router value with route type and page path and also create create ban in session
     */
    private Router rightRoute(HttpServletRequest req, Ban ban){
        Router router = new Router();

        createBanInSession(req, ban);

        router.setPagePath(JspPagePath.BAN_PAGE);
        router.setRouteType(Router.RouteType.REDIRECT);
        return router;
    }

    /**
     *
     * @param req
     * @return error router value with route type and page path and also set error message to request
     */
    private Router errorRoute(HttpServletRequest req) {
        Router router = new Router();

        String locale = req.getSession().getAttribute(RequestParameter.LOCALE) == null ? RequestParameter.DEFAULT_LOCALE : req.getSession().getAttribute(RequestParameter.LOCALE).toString();
        req.setAttribute(LabelParameter.ERROR_LABEL, LocaleFileManager.getLocale(locale).getProperty(PropertyKey.ERROR_LABEL_MESSAGE));

        router.setRouteType(Router.RouteType.FORWARD);
        router.setPagePath(JspPagePath.BAN_FORM_PAGE);

        return router;
    }

    /**
     *
     * @param req
     * @return ban value from request
     */
    private Ban init(HttpServletRequest req) {
        String banType = req.getParameter(RequestParameter.BAN_TYPE);
        String banDescription = req.getParameter(RequestParameter.BAN_DESCRIPTION);
        Ban ban = null;

        if (RequestValidator.getInstance().isValidate(banType) && RequestValidator.getInstance().isValidate(banDescription)) {
            ban = new Ban();
            ban.setBanType(banType);
            ban.setBanDescription(banDescription);
        }

        return ban;
    }

    /**
     * This method creates ban in session
     * @param req
     * @param ban
     */
    private void createBanInSession(HttpServletRequest req, Ban ban){
        List<Ban> banList = (List<Ban>) req.getSession().getAttribute(LabelParameter.BAN_LIST);
        banList.add(ban);
        req.getSession().setAttribute(LabelParameter.BAN_LIST, banList);
    }
}
