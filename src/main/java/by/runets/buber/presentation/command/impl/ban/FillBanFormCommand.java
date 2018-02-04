package by.runets.buber.presentation.command.impl.ban;

import by.runets.buber.application.service.ban.ReadBanService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.Ban;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.LabelParameter;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This class provides method to implement command to fill ban form.
 */
public class FillBanFormCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(FillBanFormCommand.class);
    private ReadBanService readBanService;

    public FillBanFormCommand(ReadBanService readBanService) {
        this.readBanService = readBanService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        String page = null;
        List<Ban> banList = null;
        String userId = req.getParameter(RequestParameter.USER_ID);

        if (RequestValidator.getInstance().isValidate(userId)) {
            req.getSession().setAttribute(RequestParameter.USER_ID, userId);
            try {
                banList = readBanService.find();
                req.getSession().setAttribute(LabelParameter.BAN_LIST, banList);
                page = JspPagePath.BAN_FORM_PAGE;
            } catch (ServiceException e) {
                LOGGER.error(e);
            }
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.FORWARD);

        return router;
    }
}
