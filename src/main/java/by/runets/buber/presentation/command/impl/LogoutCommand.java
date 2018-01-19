package by.runets.buber.presentation.command.impl;

import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        String page = null;

        HttpSession httpSession = req.getSession(false);

        if (httpSession != null){
            httpSession.invalidate();
        }

        router.setRouteType(Router.RouteType.FORWARD);
        router.setPagePath(JspPagePath.MAIN_PAGE);

        return router;
    }
}
