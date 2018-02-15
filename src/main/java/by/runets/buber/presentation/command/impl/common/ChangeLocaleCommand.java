package by.runets.buber.presentation.command.impl.common;

import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class provides method to implement change locale command.
 */
public class ChangeLocaleCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();

        String page = req.getParameter(RequestParameter.URI);
        String locale = req.getParameter(RequestParameter.LOCALE);

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute(RequestParameter.LOCALE, locale);

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.REDIRECT);

        return router;
    }
}
