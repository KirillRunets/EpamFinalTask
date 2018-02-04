package by.runets.buber.presentation.command.impl.bonus;

import by.runets.buber.application.service.bonus.SetBonusService;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetBonusCommand implements Command{
    private final static Logger LOGGER = LogManager.getLogger(DeleteBonusCommand.class);
    private SetBonusService setBonusService;

    public SetBonusCommand(SetBonusService setBonusService) {
        this.setBonusService = setBonusService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        return null;
    }
}
