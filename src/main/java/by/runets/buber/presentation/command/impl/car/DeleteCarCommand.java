package by.runets.buber.presentation.command.impl.car;

import by.runets.buber.application.service.car.DeleteCarService;
import by.runets.buber.application.service.car.ReadCarService;
import by.runets.buber.application.service.user.ReadUserService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.Car;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.LabelParameter;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.presentation.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public class DeleteCarCommand extends CarCommand implements Command{
    private final static Logger LOGGER = LogManager.getLogger(DeleteCarCommand.class);
    private DeleteCarService deleteCarService;

    public DeleteCarCommand(DeleteCarService deleteCarService) {
        this.deleteCarService = deleteCarService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        try {
            Car car = init(req);
            deleteCarService.delete(car);
            page = switchUserRole(req);
        } catch (ServiceException | ParseException e) {
            LOGGER.error(e);
        }
        return page;
    }
}
