package by.runets.buber.application.service.statistic;

import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

import java.util.Arrays;
import java.util.List;

public class RatingStatisticService {
    public boolean calculateAverageRating(Double ratingFromAnotherUser, User user) throws ServiceException {
        Double averageRating = 0.0;
        Double currentUserRating = 0.0;
        boolean isUpdated = false;

        try {
            UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);

            currentUserRating = user.getRating();

            averageRating = calculateAverageRating(ratingFromAnotherUser, currentUserRating);

            user.setRating(averageRating);
            isUpdated = userDAO.update(user);
        } catch (DAOException e) {
            throw new ServiceException("Calculate rating service exception", e);
        }
        return isUpdated;
    }

    private Double calculateAverageRating(Double ratingFromAnotherUser, Double currentUserRating){
        List<Double> ratings = Arrays.asList(ratingFromAnotherUser, currentUserRating);
        return ratings.stream()
                .mapToDouble(p -> p)
                .average()
                .getAsDouble();
    }
}
