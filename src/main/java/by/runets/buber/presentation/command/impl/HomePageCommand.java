package by.runets.buber.presentation.command.impl;

import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.presentation.command.Command;

import javax.servlet.http.HttpServletRequest;

public class HomePageCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return JspPagePath.MAIN_PAGE;
    }
}
