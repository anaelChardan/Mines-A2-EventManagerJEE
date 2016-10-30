package fr.mines.event_manager.framework.security;

import fr.mines.event_manager.app.repository.TankRepository;
import fr.mines.event_manager.framework.entity.AbstractUser;
import fr.mines.event_manager.framework.repository.utils.Field;
import fr.mines.event_manager.framework.session.SessionManager;
import fr.mines.event_manager.user.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.function.Supplier;

public class UserProvider {
    private static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    public static boolean isConnected(HttpServletRequest request) {
        return getCurrentUser(request) != null;
    }

    public static User getCurrentUser(HttpServletRequest request) {
        Integer idUser = SessionManager.get(request, CURRENT_USER_ID);

        if (idUser == null)
        {
            return null;
        }

        return TankRepository.getInstance().getUserRepository().findSingleBy(
                new Field<Integer>("id", idUser, Field.Filter.EQUAL)
        ).get();
    }

    public static boolean connect(HttpServletRequest request, Supplier<Optional<AbstractUser>> getUser) {
        Optional<AbstractUser> user = getUser.get();

        if (!user.isPresent()) {
            return false;
        }

        SessionManager.set(request, CURRENT_USER_ID, user.get().getId());

        return true;
    }

    public static void trashSession(HttpServletRequest request) {
        SessionManager.clear(request);
    }
}
