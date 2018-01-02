package by.runets.buber.presentation.command.impl.user;

import by.runets.buber.application.service.user.DeleteUserService;
import by.runets.buber.domain.entity.Role;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.presentation.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(DeleteUserCommand.class);
    private DeleteUserService deleteUserService;

    public DeleteUserCommand(DeleteUserService deleteUserService) {
        this.deleteUserService = deleteUserService;
    }

    @Override
    public String execute(HttpServletRequest req)  {

        String page = null;

        String id = req.getParameter(RequestParameter.USER_ID);
        String role = req.getParameter(RequestParameter.USER_ROLE);

        try {
            deleteUserService.delete(new User(Integer.parseInt(id), new Role(role)));
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        //reload page
        return null;
    }
}
