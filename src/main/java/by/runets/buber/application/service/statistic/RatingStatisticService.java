package by.runets.buber.application.service.statistic;

import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.ServiceException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Stream;

public class RatingStatisticService {
    public boolean calculateAverageRating(Double ratingFromAnotherUser, Integer id) throws ServiceException {
        Double averageRating = 0.0;
        Double currentUserRating = 0.0;
        boolean isUpdated = false;

        try {
            UserDAO userDAO = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
            User user = userDAO.find(id);

            currentUserRating = user.getRating();
            averageRating = calculateAverageRating(ratingFromAnotherUser, currentUserRating);

            isUpdated = userDAO.updateUserRating(id, averageRating);
        } catch (DAOException e) {
            throw new ServiceException("Calculate rating service exception", e);
        }
        return isUpdated;
    }

    private Double calculateAverageRating(Double ratingFromAnotherUser, Double currentUserRating){
        OptionalDouble optional = Stream.of(ratingFromAnotherUser, currentUserRating)
                .mapToDouble(p -> p)
                .average();
        return optional.isPresent() ? optional.getAsDouble() : 0.0;
    }
}
