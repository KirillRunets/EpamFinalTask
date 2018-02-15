package by.runets.buber.application.service.order.comparator;

import by.runets.buber.domain.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.HashMap;

public class DistanceComparator implements Comparator<User> {
    private User passenger;
    public DistanceComparator(User passenger) {
        this.passenger = passenger;
    }

    @Override
    public int compare(User driver1, User driver2) {
        Double distanceBetweenFirstDriverAndPassenger = Math.hypot((driver1.getCurrentLocation().getLatitude() - passenger.getCurrentLocation().getLatitude()), (driver1.getCurrentLocation().getLongitude() - passenger.getCurrentLocation().getLongitude()));
        Double distanceBetweenSecondDriverAndPassenger = Math.hypot((driver2.getCurrentLocation().getLatitude() - passenger.getCurrentLocation().getLatitude()), (driver2.getCurrentLocation().getLongitude() - passenger.getCurrentLocation().getLongitude()));

        if (distanceBetweenFirstDriverAndPassenger < distanceBetweenSecondDriverAndPassenger){
            return -1;
        }
        if (distanceBetweenFirstDriverAndPassenger > distanceBetweenSecondDriverAndPassenger){
            return 1;
        }

        return 0;
    }
}
