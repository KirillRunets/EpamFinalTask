package by.runets.buber.presentation.command.impl.user;

import by.runets.buber.application.service.user.DeleteUserService;
import by.runets.buber.application.service.user.ReadUserService;
import by.runets.buber.domain.entity.Role;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.LabelParameter;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.exception.ServiceException;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * This class provides methods to implement delete user command
 */
public class DeleteUserCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(DeleteUserCommand.class);
    private DeleteUserService deleteUserService;

    public DeleteUserCommand(DeleteUserService deleteUserService) {
        this.deleteUserService = deleteUserService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res)  {
        Router router = new Router();
        String page = null;

        String id = req.getParameter(RequestParameter.USER_ID);
        String role = req.getParameter(RequestParameter.USER_ROLE);

        try {
            deleteUserService.delete(id, role);
            page = switchUserRole(req, role, new Integer(id));
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.REDIRECT);

        return router;
    }

    private String setDataToPageByRole(HttpServletRequest req, String role, String listLabel) throws ServiceException {
        List<User> userList = new ReadUserService().find(role);
        if (userList != null){
            req.setAttribute(listLabel, userList);
        }
        return role.equalsIgnoreCase(UserRoleType.DRIVER) ? JspPagePath.DRIVER_ALL_INFO_PAGE : JspPagePath.PASSENGER_ALL_INFO_PAGE;
    }

    private String switchUserRole(HttpServletRequest req, String role, int id) throws ServiceException {
        String page = null;
        switch (role.toUpperCase()){
            case UserRoleType.DRIVER:
                updateUserInSession(req, id, LabelParameter.DRIVER_LIST_LABEL);
                page = setDataToPageByRole(req, UserRoleType.DRIVER, LabelParameter.DRIVER_LIST_LABEL);
                break;
            case UserRoleType.PASSENGER:
                updateUserInSession(req, id, LabelParameter.PASSENGER_LIST_LABEL);
                page = setDataToPageByRole(req, UserRoleType.PASSENGER, LabelParameter.PASSENGER_LIST_LABEL);
                break;
        }
        return page;
    }

    private void updateUserInSession(HttpServletRequest req, int deletedUserId, String label){
        HttpSession httpSession = req.getSession();
        List<User> userList = (List<User>) httpSession.getAttribute(label);
        userList.removeIf(driver -> driver.getId() == deletedUserId);
        httpSession.removeAttribute(label);
        httpSession.setAttribute(label, userList);
    }

}
