package by.runets.buber.presentation.command.impl.ban;

import by.runets.buber.application.service.ban.SetBanService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.Ban;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.infrastructure.util.NumberFormatLocaleFactory;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SetBanCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(SetBanCommand.class);
    private SetBanService banService;

    public SetBanCommand(SetBanService banService) {
        this.banService = banService;
    }

    @Override
    public Router execute(HttpServletRequest req) {
        Router router = new Router();
        String page = null;
        try {
            User user = init(req);
            banService.setBan(user);
            page = JspPagePath.ADMIN_HOME_PAGE;
        } catch (ServiceException | ParseException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.FORWARD);

        return router;
    }

    private User init(HttpServletRequest req) throws ParseException {
        User user = null;
        String locale = req.getSession().getAttribute(RequestParameter.LOCALE) == null ? RequestParameter.DEFAULT_LOCALE : req.getSession().getAttribute(RequestParameter.LOCALE).toString();
        String userId = (String) req.getSession(false).getAttribute(RequestParameter.USER_ID);
        String banId = req.getParameter(RequestParameter.BAN_ID);
        String unBanDate = req.getParameter(RequestParameter.UNBAN_DATE);
        String numberFormatPattern = NumberFormatLocaleFactory.factory(locale);

        if (RequestValidator.getInstance().isValidateBanData(userId, unBanDate, banId)){
            user = new User(new Integer(userId), new Ban(new Integer(banId)), new SimpleDateFormat(numberFormatPattern).parse(unBanDate));
        }

        return user;
    }


}
