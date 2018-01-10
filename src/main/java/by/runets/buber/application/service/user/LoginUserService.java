package by.runets.buber.application.service.user;

import by.runets.buber.domain.entity.Ban;
import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.dao.AbstractDAO;
import by.runets.buber.infrastructure.dao.UserDAO;
import by.runets.buber.infrastructure.constant.DAOType;
import by.runets.buber.infrastructure.dao.factory.DAOFactory;
import by.runets.buber.infrastructure.exception.DAOException;
import by.runets.buber.infrastructure.exception.EncryptorException;
import by.runets.buber.infrastructure.util.PasswordEncrypt;

import java.util.List;
import java.util.stream.Collectors;

public class LoginUserService {
    public User authenticateUser(String login, String password) throws DAOException {
        UserDAO dao = (UserDAO) DAOFactory.getInstance().createDAO(DAOType.USER_DAO_TYPE);
        AbstractDAO banDAO = DAOFactory.getInstance().createDAO(DAOType.BAN_DAO_TYPE);
        List<Ban> banList =  banDAO.findAll();
        User user = dao.checkEmailPassword(login, PasswordEncrypt.encryptPassword(password));
        if (user != null){
            joinBanToUser(banList, user);
        }
        return user;
    }

    private void joinBanToUser(List<Ban> banList, User user){
        Ban ban = banList.stream()
                .filter(b -> b.getId() == user.getBan().getId())
                .findFirst().orElse(null);
        user.setBan(ban);
    }

}
