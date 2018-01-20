package by.runets.buber.presentation.command.impl.order;

import by.runets.buber.application.service.order.MakeOrderService;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MakeOrderCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(MakeOrderCommand.class);
    private MakeOrderService makeOrderService;

    public MakeOrderCommand(MakeOrderService makeOrderService) {
        this.makeOrderService = makeOrderService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();



        return null;
    }
}
