package by.runets.buber.presentation.command.impl.user;

import by.runets.buber.application.service.user.UpdateUserService;
import by.runets.buber.presentation.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UpdateDriverCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(UpdateDriverCommand.class);
    private UpdateUserService updateUserService;

    public UpdateDriverCommand(UpdateUserService updateUserService) {
        this.updateUserService = updateUserService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        return null;
    }
}
