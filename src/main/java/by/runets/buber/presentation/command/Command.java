package by.runets.buber.presentation.command;


import by.runets.buber.presentation.controller.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    Router execute(HttpServletRequest req, HttpServletResponse res);
}
