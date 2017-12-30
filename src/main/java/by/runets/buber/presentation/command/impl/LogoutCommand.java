package by.runets.buber.presentation.command.impl;

import by.runets.buber.presentation.command.Command;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return null;
    }
}
