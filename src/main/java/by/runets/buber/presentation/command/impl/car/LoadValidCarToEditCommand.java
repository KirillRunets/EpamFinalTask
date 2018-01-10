package by.runets.buber.presentation.command.impl.car;

import by.runets.buber.application.service.car.ReadCarService;
import by.runets.buber.domain.entity.Car;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.LabelParameter;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.presentation.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class LoadValidCarToEditCommand extends CarCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(LoadValidCarToEditCommand.class);
    private ReadCarService readCarService;

    public LoadValidCarToEditCommand(ReadCarService readCarService) {
        this.readCarService = readCarService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;

        try {
            Car car = init(req);
            car = readCarService.findCarById(car.getId());
            req.setAttribute(LabelParameter.CAR, car);
            page = JspPagePath.CAR_FORM_PAGE;
        } catch (ParseException | ServiceException e) {
            LOGGER.error(e);
        }

        return page;
    }
}
