package fr.mines.event_manager.framework.security;

import fr.mines.event_manager.app.repository.TankRepository;
import fr.mines.event_manager.framework.repository.Field;
import fr.mines.event_manager.user.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginAuthenticator {
    public static Optional<User> checkConnexion(HttpServletRequest request){
        return TankRepository.getInstance().getUserRepository().findSingleBy(
            new Field("email", request.getParameter("email"), Field.Filter.EQUAL),
            new Field("password", request.getParameter("password"), Field.Filter.EQUAL)
        );
    }
}
