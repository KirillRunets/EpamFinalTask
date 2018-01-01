package by.runets.buber.presentation.command.impl;

import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.presentation.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {

        String locale = req.getParameter(RequestParameter.LOCALE);
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute(RequestParameter.LOCALE, locale);

        return null;
    }
}
