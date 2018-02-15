package by.runets.buber.presentation.command.impl.common;

import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmptyCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        return null;
    }
}
