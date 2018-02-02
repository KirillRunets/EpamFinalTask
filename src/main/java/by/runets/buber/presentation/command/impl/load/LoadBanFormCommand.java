package by.runets.buber.presentation.command.impl.load;

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

public class LoadBanFormCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(LoadBanFormCommand.class);
    private ReadBanService readBanService;

    public LoadBanFormCommand(ReadBanService readBanService) {
        this.readBanService = readBanService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        String banId = req.getParameter(RequestParameter.BAN_ID);

        try {
            if (RequestValidator.getInstance().isValidate(banId)){
                Ban ban = readBanService.find(new Integer(banId));
                req.getSession().setAttribute(LabelParameter.BAN, ban);
            } else {
                req.getSession().removeAttribute(LabelParameter.BAN);
            }
        } catch (ServiceException e){
            LOGGER.error(e);
        }

        router.setRouteType(Router.RouteType.REDIRECT);
        router.setPagePath(JspPagePath.SECOND_BAN_FORM_PAGE);

        return router;
    }
}
