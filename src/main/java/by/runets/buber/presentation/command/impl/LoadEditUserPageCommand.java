package by.runets.buber.presentation.command.impl;

import by.runets.buber.application.service.user.ReadUserService;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LoadEditUserPageCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(LoadEditUserPageCommand.class);
    private ReadUserService readUserService;

    public LoadEditUserPageCommand(ReadUserService readUserService) {
        this.readUserService = readUserService;
    }

    @Override
    public Router execute(HttpServletRequest req) {
        User user = null;
        Router router = new Router();


        String userId = req.getParameter(RequestParameter.USER_ID);

        try {
            user = readUserService.find(Integer.parseInt(userId));
            req.getSession().setAttribute(RequestParameter.USER, user);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        router.setPagePath(JspPagePath.EDIT_USER_PAGE);
        router.setRouteType(Router.RouteType.REDIRECT);

        return router;
    }
}
