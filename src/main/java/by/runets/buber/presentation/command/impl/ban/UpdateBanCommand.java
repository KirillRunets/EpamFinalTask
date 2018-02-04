package by.runets.buber.presentation.command.impl.ban;

import by.runets.buber.application.service.ban.UpdateBanService;
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
 * This class provides method to implement update ban command.
 */
public class UpdateBanCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(UpdateBanCommand.class);
    private UpdateBanService updateBanService;

    public UpdateBanCommand(UpdateBanService updateBanService) {
        this.updateBanService = updateBanService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        Ban ban = null;
        try{
            router = (ban = init(req)) != null
                    ? updateBanService.update(ban)
                    ? rightRoute(req, ban) : errorRoute(req) : errorRoute(req);
        } catch (ServiceException e){
            LOGGER.error(e);
        }

        return router;
    }

    /**
     *
     * @param req
     * @param ban
     * @return right router value with route type and page path and also update ban in session.
     */
    private Router rightRoute(HttpServletRequest req, Ban ban){
        Router router = new Router();

        updateBanInSession(req, ban);

        router.setPagePath(JspPagePath.BAN_PAGE);
        router.setRouteType(Router.RouteType.REDIRECT);
        return router;
    }

    /**
     *
     * @param req
     * @return error router value with route type and page path and set error message to request.
     */
    private Router errorRoute(HttpServletRequest req) {
        Router router = new Router();

        String locale = req.getSession().getAttribute(RequestParameter.LOCALE) == null ? RequestParameter.DEFAULT_LOCALE : req.getSession().getAttribute(RequestParameter.LOCALE).toString();
        req.setAttribute(LabelParameter.ERROR_LABEL, LocaleFileManager.getLocale(locale).getProperty(PropertyKey.ERROR_LABEL_MESSAGE));

        router.setPagePath(JspPagePath.BAN_FORM_PAGE);
        router.setRouteType(Router.RouteType.FORWARD);

        return router;
    }

    /**
     * This method initialize ban object from request parameters.
     * @param req
     * @return ban object if request parameters valid else null
     */
    private Ban init(HttpServletRequest req){
        Ban ban = null;

        String banId = req.getParameter(RequestParameter.BAN_ID);
        String banType = req.getParameter(RequestParameter.BAN_TYPE);
        String banDescription = req.getParameter(RequestParameter.BAN_DESCRIPTION);

        if (RequestValidator.getInstance().isValidate(banId) && RequestValidator.getInstance().isValidate(banType) && RequestValidator.getInstance().isValidate(banDescription)){
            ban = new Ban();
            ban.setId(new Integer(banId));
            ban.setBanType(banType);
            ban.setBanDescription(banDescription);
        }

        return ban;
    }

    /**
     * This method update ban list in session.
     * @param req
     * @param ban
     */
    private void updateBanInSession(HttpServletRequest req, Ban ban){
        List<Ban> banList = (List<Ban>) req.getSession().getAttribute(LabelParameter.BAN_LIST);

        banList.removeIf(b -> b.getId() == ban.getId());
        banList.add(ban);

        req.getSession().setAttribute(LabelParameter.BAN_LIST, banList);
    }
}
