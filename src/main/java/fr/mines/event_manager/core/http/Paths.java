package fr.mines.event_manager.core.http;

import fr.mines.event_manager.framework.router.http.Route;
import fr.mines.event_manager.framework.router.utils.ServletConsumer;
import fr.mines.event_manager.framework.router.utils.WrappedServletAction;

import java.util.regex.Pattern;

public class Paths {
    /**************
     * GET ROUTES
     **************/
    public static final Route getLogin(ServletConsumer<WrappedServletAction> ServletConsumer)
    {
        return new Route(Pattern.compile("/login"), Route.PROTECTION_LEVEL.NONE, ServletConsumer);
    }
    public static final Route getSubscribe(ServletConsumer<WrappedServletAction> ServletConsumer)
    {
        return new Route(Pattern.compile("/subscribe"), Route.PROTECTION_LEVEL.NONE, ServletConsumer);
    }
    public static final Route getHome(ServletConsumer<WrappedServletAction> ServletConsumer) {
        return new Route(Pattern.compile("/"), Route.PROTECTION_LEVEL.CONNECTED, ServletConsumer);
    }

    public static final Route getIndexEvent(ServletConsumer<WrappedServletAction> ServletConsumer) {
        return new Route(Pattern.compile("/"), Route.PROTECTION_LEVEL.CONNECTED, ServletConsumer);
    }

    public static final Route getOneEvent(ServletConsumer<WrappedServletAction> ServletConsumer) {
        return new Route(Pattern.compile("/(?<id>\\d+)"), Route.PROTECTION_LEVEL.CONNECTED, ServletConsumer);
    }

    public static final Route getCreateEvent(ServletConsumer<WrappedServletAction> ServletConsumer) {
        return new Route(Pattern.compile("/new"), Route.PROTECTION_LEVEL.CONNECTED, ServletConsumer);
    }

    public static final Route getEditEvent(ServletConsumer<WrappedServletAction> ServletConsumer) {
        return new Route(Pattern.compile("/edit"), Route.PROTECTION_LEVEL.CONNECTED, ServletConsumer);
    }

    public static Route postSubscribeToEvent(ServletConsumer<WrappedServletAction> ServletConsumer) {
        return new Route(Pattern.compile("/?<id>\\d+"), Route.PROTECTION_LEVEL.CONNECTED, ServletConsumer);
    }

    public static final Route getProfile(ServletConsumer<WrappedServletAction> ServletConsumer)
    {
        return new Route(Pattern.compile("/profile"), Route.PROTECTION_LEVEL.CONNECTED, ServletConsumer);
    }

    /**************
     * POST ROUTES
     **************/
    public static final Route postLogin (ServletConsumer<WrappedServletAction> ServletConsumer) {
        return new Route(Pattern.compile("/loginpost"), Route.PROTECTION_LEVEL.NONE, ServletConsumer);
    }

    public static final Route postCreateEvent(ServletConsumer<WrappedServletAction> ServletConsumer) {
        return new Route(Pattern.compile("/new"), Route.PROTECTION_LEVEL.CONNECTED, ServletConsumer);
    }

    public static final Route postSubscribe(ServletConsumer<WrappedServletAction> ServletConsumer) {
        return new Route(Pattern.compile("/subscribe"), Route.PROTECTION_LEVEL.NONE, ServletConsumer);
    }

    public static final Route postEditEvent(ServletConsumer<WrappedServletAction> ServletConsumer) {
        return new Route(Pattern.compile("/edit"), Route.PROTECTION_LEVEL.CONNECTED, ServletConsumer);
    }
    public static final Route postActionEvent(ServletConsumer<WrappedServletAction> ServletConsumer) {
        return new Route(Pattern.compile("/action"), Route.PROTECTION_LEVEL.CONNECTED, ServletConsumer);
    }
}
