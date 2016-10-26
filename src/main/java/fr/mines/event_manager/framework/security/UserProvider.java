package fr.mines.event_manager.framework.security;

import fr.mines.event_manager.app.repository.TankRepository;
import fr.mines.event_manager.framework.entity.AbstractUser;
import fr.mines.event_manager.framework.repository.utils.Field;
import fr.mines.event_manager.user.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.function.Supplier;

public class UserProvider {
    private static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    public static boolean isConnected(HttpServletRequest request)
    {
        return getCurrentUser(request) != null;
    }

    protected static HttpSession getSession(HttpServletRequest request, boolean tryWithArg)
    {
        if (tryWithArg)
        {
            return request.getSession(false) == null ? request.getSession() : request.getSession(false);
        }

        return request.getSession(false);
    }

    protected static void bindUser(HttpServletRequest request, AbstractUser user)
    {
        getSession(request, true).setAttribute(CURRENT_USER_ID, user.getId());
    }

    public static User getCurrentUser(HttpServletRequest request)
    {
        return getSession(request, false) != null ?
                TankRepository.getInstance().getUserRepository().findSingleBy(
                    new Field<Integer>(
                        "id",
                        Integer.parseInt((String)getSession(request, false).getAttribute(CURRENT_USER_ID)),
                        Field.Filter.EQUAL
                    )
                ).get() : null;
    }

    public static boolean connect(HttpServletRequest request, Supplier<Optional<AbstractUser>> getUser){
        Optional<AbstractUser> user = getUser.get();

        if (!user.isPresent())
        {
            return false;
        }

        bindUser(request, user.get());

        return true;
    }

    public static void trashSession(HttpServletRequest request)
    {
        if (getSession(request, false) != null) {
            getSession(request, false).invalidate();
        }
    }
}
