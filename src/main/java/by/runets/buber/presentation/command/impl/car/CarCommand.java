package by.runets.buber.presentation.command.impl.car;

import by.runets.buber.application.service.car.ReadCarService;
import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.Car;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.infrastructure.constant.LabelParameter;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public class CarCommand {
    public Car init(HttpServletRequest req) throws ParseException {
        Car car = null;
        User user = (User) req.getSession(false).getAttribute(UserRoleType.USER);
        String carId = req.getParameter(RequestParameter.CAR_ID);
        if (RequestValidator.getInstance().isValidate(carId)){
            car = new Car(new Integer(carId), user);
        }
        return car;
    }

    private String setCarListToPage(HttpServletRequest req, String label, User user) throws ServiceException {
        List<Car> carList = new ReadCarService().findValidCars();
        if (carList != null){
            req.setAttribute(label, carList);
        }
        return user.getRole().getRoleName().equalsIgnoreCase(UserRoleType.ADMIN) ? JspPagePath.ADMIN_CAR_LIST_PAGE : JspPagePath.DRIVER_CAR_PROFILE_PAGE;
    }


    String switchUserRole(HttpServletRequest req) throws ServiceException {
        String page = null;
        User user = (User) req.getSession(false).getAttribute(UserRoleType.USER);
        page = setCarListToPage(req, LabelParameter.ADMIN_CAR_LIST, user);
        return page;
    }
}
