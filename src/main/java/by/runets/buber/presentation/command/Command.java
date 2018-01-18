package by.runets.buber.presentation.command;


import by.runets.buber.presentation.controller.Router;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest req);
}
