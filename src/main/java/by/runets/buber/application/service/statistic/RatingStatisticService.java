package by.runets.buber.application.service.statistic;

import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

import java.util.OptionalDouble;
import java.util.stream.Stream;

/**
 * This class provides method to calculate user rating statistic.
 */
public class RatingStatisticService {
    public boolean calculateAverageRating(Double ratingFromAnotherUser, Integer id) throws ServiceException {
        Double averageRating = 0.0;
        Double currentUserRating = 0.0;
        Integer tripAmount = 0;
        boolean isUpdated = false;

        try {
            UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
            User user = userDAO.find(id);

            currentUserRating = user.getRating();
            tripAmount = user.getTripAmount();
            averageRating = calculateAverageRating(ratingFromAnotherUser, currentUserRating, tripAmount);

            isUpdated = userDAO.updateUserRating(id, averageRating);
        } catch (DAOException e) {
            throw new ServiceException("Calculate rating service exception", e);
        }
        return isUpdated;
    }

    private Double calculateAverageRating(Double ratingFromAnotherUser, Double currentUserRating, int tripAmount){
        OptionalDouble optional = Stream.of(ratingFromAnotherUser, currentUserRating)
                .mapToDouble(p -> p)
                .average();
        Double average = optional.getAsDouble();
        return average + tripAmount / 100;
    }
}
