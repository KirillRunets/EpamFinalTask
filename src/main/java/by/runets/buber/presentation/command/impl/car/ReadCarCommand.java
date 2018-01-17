package by.runets.buber.presentation.command.impl.car;

import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.presentation.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class ReadCarCommand extends CarCommand implements Command{
    private final static Logger LOGGER = LogManager.getLogger(ReadCarCommand.class);

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        try {
            page = switchUserRole(req);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }
        return page;
    }
}
