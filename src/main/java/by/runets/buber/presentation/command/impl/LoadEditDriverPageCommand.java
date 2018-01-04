package by.runets.buber.presentation.command.impl;

import by.runets.buber.application.service.user.ReadUserService;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.presentation.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LoadEditDriverPageCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(LoadEditDriverPageCommand.class);
    private ReadUserService readUserService;

    public LoadEditDriverPageCommand(ReadUserService readUserService) {
        this.readUserService = readUserService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        User user = null;

        String userId = req.getParameter(RequestParameter.USER_ID);

        try {
            user = readUserService.find(Integer.parseInt(userId));
            req.setAttribute(RequestParameter.DRIVER, user);
            page = JspPagePath.EDIT_DRIVER_PAGE;
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        return page;
    }
}
