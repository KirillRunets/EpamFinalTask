package by.runets.buber.application.service.order.comparator;

import by.runets.buber.domain.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

public class DistanceComparator implements Comparator<User> {
    private User passenger;

    public DistanceComparator(User passenger) {
        this.passenger = passenger;
    }

    @Override
    public int compare(User driver1, User driver2) {
        Double distanceBetweenFirstDriverAndPassenger = Math.hypot((driver1.getCurrentLocation().getX() - passenger.getCurrentLocation().getX()), (driver1.getCurrentLocation().getY() - passenger.getCurrentLocation().getY()));
        Double distanceBetweenSecondDriverAndPassenger = Math.hypot((driver2.getCurrentLocation().getX() - passenger.getCurrentLocation().getX()), (driver2.getCurrentLocation().getY() - passenger.getCurrentLocation().getY()));

        if (distanceBetweenFirstDriverAndPassenger < distanceBetweenSecondDriverAndPassenger){
            return -1;
        }
        if (distanceBetweenFirstDriverAndPassenger > distanceBetweenSecondDriverAndPassenger){
            return 1;
        }

        return 0;
    }
}
