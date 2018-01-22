package by.runets.buber.presentation.command.impl.order;

import by.runets.buber.application.service.order.ConfirmOrderService;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmOrderCommand implements Command{
    private final static Logger LOGGER = LogManager.getLogger(ConfirmOrderCommand.class);
    private ConfirmOrderService confirmOrderService;

    public ConfirmOrderCommand(ConfirmOrderService confirmOrderService) {
        this.confirmOrderService = confirmOrderService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        return null;
    }
}
