package by.runets.buber.presentation.command;

import by.runets.buber.infrastructure.exception.DAOException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest req);
}
