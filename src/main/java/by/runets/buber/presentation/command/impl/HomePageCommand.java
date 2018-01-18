package by.runets.buber.presentation.command.impl;

import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;

import javax.servlet.http.HttpServletRequest;

public class HomePageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req) {
        Router router = new Router();
        router.setRouteType(Router.RouteType.FORWARD);
        router.setPagePath(JspPagePath.MAIN_PAGE);
        return router;
    }
}
