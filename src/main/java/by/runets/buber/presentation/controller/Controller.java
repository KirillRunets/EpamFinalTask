package by.runets.buber.presentation.controller;

import by.runets.buber.infrastructure.constant.PropertyPath;
import by.runets.buber.infrastructure.constant.PropertyKey;
import by.runets.buber.infrastructure.exception.IOFileException;
import by.runets.buber.infrastructure.util.PropertyFileManager;
import by.runets.buber.presentation.command.ActionFactory;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.command.impl.EmptyCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;


@WebServlet("/Controller")
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
        Optional<Command> commandOptional = ActionFactory.defineCommand(req.getParameter("command"));
        Command command = commandOptional.orElse(new EmptyCommand());
        String page = command.execute(req);

        if (page != null){
            HttpSession session = req.getSession();
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(page);
            req.getSession().setAttribute("locale", req.getParameter("locale"));
            requestDispatcher.forward(req, res);
        } else {

            /*req.getSession().setAttribute("nullPage", req.getSession().ge);
*/
            try {
                res.sendRedirect(new PropertyFileManager(PropertyPath.CONFIG_FILE).getValue(PropertyKey.INDEX_PAGE));
            } catch (IOFileException e) {
                LOGGER.fatal(e);
                throw new RuntimeException(e);
            }
        }
    }
}