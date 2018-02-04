package by.runets.buber.presentation.command.impl.load;

import by.runets.buber.application.service.bonus.ReadBonusService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.Ban;
import by.runets.buber.domain.entity.Bonus;
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

public class LoadBonusFormCommand implements Command{
    private final static Logger LOGGER = LogManager.getLogger(LoadBonusFormCommand.class);
    private ReadBonusService readBonusService;

    public LoadBonusFormCommand(ReadBonusService readBonusService) {
        this.readBonusService = readBonusService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        String bonusId = req.getParameter(RequestParameter.BONUS_ID);

        try {
            if (RequestValidator.getInstance().isValidate(bonusId)){
                Bonus bonus = readBonusService.read(new Integer(bonusId));
                req.getSession().setAttribute(LabelParameter.BONUS, bonus);
            } else {
                req.getSession().removeAttribute(LabelParameter.BONUS);
            }
        } catch (ServiceException e){
            LOGGER.error(e);
        }

        router.setRouteType(Router.RouteType.REDIRECT);
        router.setPagePath(JspPagePath.BONUS_FORM);

        return router;
    }
}
