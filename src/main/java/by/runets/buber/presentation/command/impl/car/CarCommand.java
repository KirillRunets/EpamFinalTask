package by.runets.buber.presentation.command.impl.car;

import by.runets.buber.application.validation.RequestValidator;
import by.runets.buber.domain.entity.Car;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.LabelParameter;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.infrastructure.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

class CarCommand {
    Car init(HttpServletRequest req) throws ParseException {
        Car car = null;
        String carId = req.getParameter(RequestParameter.CAR_ID);
        if (RequestValidator.getInstance().isValidate(carId)){
            car = new Car(new Integer(carId));
        }
        return car;
    }

    void setCarListToSession(HttpServletRequest req, String label, List<Car> carList) throws ServiceException {
        if (carList != null && label != null){
            req.getSession().setAttribute(label, carList);
        }
    }

    void updateCarListInSession(HttpServletRequest req, String label, Car newCar){
        HttpSession httpSession = req.getSession();
        httpSession.removeAttribute(LabelParameter.CAR);
        List<Car> sessionCarList = (List<Car>) httpSession.getAttribute(label);
        sessionCarList = sessionCarList.stream()
                .map(car -> {
                    if (car.getId() == newCar.getId()){
                        return newCar;
                    } else {
                        return car;
                    }
                })
                .collect(Collectors.toList());
        httpSession.removeAttribute(label);
        httpSession.setAttribute(label, sessionCarList);
    }

    void deleteCarInCarListSession(HttpServletRequest req, String label, int deletedCarId){
        HttpSession httpSession = req.getSession();
        List<Car> carList = (List<Car>) httpSession.getAttribute(label);
        carList.removeIf(car1 -> car1.getId() == deletedCarId);
        httpSession.removeAttribute(label);
        httpSession.setAttribute(label, carList);
    }

    void deleteCarInUserSession(HttpServletRequest req){
        HttpSession httpSession = req.getSession();
        User user = (User) httpSession.getAttribute(UserRoleType.USER);
        user.setCar(null);
        httpSession.removeAttribute(UserRoleType.USER);
        httpSession.setAttribute(UserRoleType.USER, user);
    }

    void setCarToUserSession(HttpServletRequest req, Car car){
        HttpSession httpSession = req.getSession();
        User user = (User) httpSession.getAttribute(UserRoleType.USER);
        user.setCar(car);
        httpSession.removeAttribute(UserRoleType.USER);
        httpSession.setAttribute(UserRoleType.USER, user);
    }
}
