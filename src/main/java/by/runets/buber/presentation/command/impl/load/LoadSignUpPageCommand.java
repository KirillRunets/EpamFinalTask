package by.runets.buber.presentation.command.impl.load;

import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoadSignUpPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();
        router.setRouteType(Router.RouteType.FORWARD);
        router.setPagePath(JspPagePath.SIGN_UP_PAGE);
        return router;
    }
}
