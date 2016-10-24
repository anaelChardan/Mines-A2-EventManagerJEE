package fr.mines.event_manager.core.http;

import fr.mines.event_manager.framework.router.http.Route;
import fr.mines.event_manager.framework.router.utils.WrappedServletAction;

import java.util.function.Consumer;
import java.util.regex.Pattern;

public class Paths {
    /**************
     * GET ROUTES
     **************/
    public static final Route getLogin(Consumer<WrappedServletAction> consumer)
    {
        return new Route(Pattern.compile("/login"), Route.PROTECTION_LEVEL.NONE, consumer);
    }
    public static final Route getHome(Consumer<WrappedServletAction> consumer) {
        return new Route(Pattern.compile("/"), Route.PROTECTION_LEVEL.CONNECTED, consumer);
    }


    public static final Route getIndexEvent(Consumer<WrappedServletAction> consumer) {
        return new Route(Pattern.compile("/"), Route.PROTECTION_LEVEL.CONNECTED, consumer);
    }

    public static final Route getOneEvent(Consumer<WrappedServletAction> consumer) {
        return new Route(Pattern.compile("/(?<id>\\d+)"), Route.PROTECTION_LEVEL.CONNECTED, consumer);
    }

    public static final Route getCreateEvent(Consumer<WrappedServletAction> consumer) {
        return new Route(Pattern.compile("/new"), Route.PROTECTION_LEVEL.CONNECTED, consumer);
    }

    public static final Route getProfile(Consumer<WrappedServletAction> consumer)
    {
        return new Route(Pattern.compile("/profile"), Route.PROTECTION_LEVEL.CONNECTED, consumer);
    }

    /**************
     * POST ROUTES
     **************/
    public static final Route postLogin (Consumer<WrappedServletAction> consumer) {
        return new Route(Pattern.compile("/loginpost"), Route.PROTECTION_LEVEL.NONE, consumer);
    }

    public static final Route postCreateEvent(Consumer<WrappedServletAction> consumer) {
        return new Route(Pattern.compile("/newEvent"), Route.PROTECTION_LEVEL.CONNECTED, consumer);
    }
}
