package fr.mines.event_manager.home_made_framework.security;

import fr.mines.event_manager.home_made_framework.entity.AbstractUser;
import fr.mines.event_manager.home_made_framework.session.SessionManager;
import fr.mines.event_manager.user.entity.User;
import fr.mines.event_manager.user.manager.UserManager;

import javax.servlet.http.HttpServletRequest;
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

        return UserManager.getInstance().find(idUser).get();
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
