package by.runets.buber.presentation.controller;

import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.presentation.command.ActionFactory;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.command.impl.common.EmptyCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * This class realize front controller pattern.
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
    private final static Logger LOGGER = LogManager.getLogger(Controller.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LOGGER.debug(req.getParameter(RequestParameter.COMMAND));
        Optional<Command> commandOptional = ActionFactory.defineCommand(req.getParameter(RequestParameter.COMMAND));
        Command command = commandOptional.orElse(new EmptyCommand());
        Router router = command.execute(req, res);
        if (router != null){
            String page = router.getPagePath();
            if (router.getRouteType() == Router.RouteType.FORWARD) {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher(page);
                requestDispatcher.forward(req, res);
            } else {
                res.sendRedirect(req.getContextPath() + page);
            }
        } else {
            req.getSession().invalidate();
            res.sendRedirect(JspPagePath.MAIN_PAGE);
        }
    }
}
