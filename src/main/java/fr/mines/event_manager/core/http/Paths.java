package fr.mines.event_manager.core.http;

import fr.mines.event_manager.framework.router.http.Route;
import fr.mines.event_manager.framework.router.utils.ServletConsumer;
import fr.mines.event_manager.framework.router.utils.WrappedServletAction;

import java.util.regex.Pattern;

public class Paths {
    /**************
     * GET ROUTES *
     **************/

    /***
     * AppServlet /app
     ***/

    public static final Route getLogin(ServletConsumer<WrappedServletAction> ServletConsumer)
    {
        return new Route(Pattern.compile("/login"), Route.PROTECTION_LEVEL.NONE, ServletConsumer);
    }

    public static final Route getLogout(ServletConsumer<WrappedServletAction> ServletConsumer)
    {
        return new Route(Pattern.compile("/logout"), Route.PROTECTION_LEVEL.NONE, ServletConsumer);
    }

    /***
     * UserServlet /user
     ***/

    public static final Route getSubscribe(ServletConsumer<WrappedServletAction> ServletConsumer)
    {
        return new Route(Pattern.compile("/subscribe"), Route.PROTECTION_LEVEL.NONE, ServletConsumer);
    }

    public static final Route getProfile(ServletConsumer<WrappedServletAction> ServletConsumer)
    {
        return new Route(Pattern.compile("/profile"), Route.PROTECTION_LEVEL.CONNECTED, ServletConsumer);
    }

    /***
     * HomeServlet /
     ***/

    public static final Route getHome(ServletConsumer<WrappedServletAction> ServletConsumer) {
        return new Route(Pattern.compile("/"), Route.PROTECTION_LEVEL.CONNECTED, ServletConsumer);
    }

    /***
     * EventServlet /event
     ***/

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
        return new Route(Pattern.compile("/(?<id>\\d+)/edit"), Route.PROTECTION_LEVEL.PROPRIETARY, ServletConsumer);
    }

    /**************
     * POST ROUTES
     **************/

    /***
     * UserServlet /user
     ***/

    public static final Route postLogin (ServletConsumer<WrappedServletAction> ServletConsumer) {
        return new Route(Pattern.compile("/loginpost"), Route.PROTECTION_LEVEL.NONE, ServletConsumer);
    }

    public static final Route postSubscribe(ServletConsumer<WrappedServletAction> ServletConsumer) {
        return new Route(Pattern.compile("/subscribe"), Route.PROTECTION_LEVEL.NONE, ServletConsumer);
    }

    /***
     * EventServlet /event
     ***/

    public static final Route postCreateEvent(ServletConsumer<WrappedServletAction> ServletConsumer) {
        return new Route(Pattern.compile("/new"), Route.PROTECTION_LEVEL.CONNECTED, ServletConsumer);
    }

    public static final Route postSubscribeToEvent(ServletConsumer<WrappedServletAction> ServletConsumer) {
        return new Route(Pattern.compile("/(?<id>\\d+)/subscribe"), Route.PROTECTION_LEVEL.CONNECTED, ServletConsumer);
    }

    public static final Route postUnsubscribeToEvent(ServletConsumer<WrappedServletAction> ServletConsumer) {
        return new Route(Pattern.compile("/(?<id>\\d+)/unsubscribe"), Route.PROTECTION_LEVEL.CONNECTED, ServletConsumer);
    }

    public static final Route postEditEvent(ServletConsumer<WrappedServletAction> ServletConsumer) {
        return new Route(Pattern.compile("/(?<id>\\d+)/edit"), Route.PROTECTION_LEVEL.PROPRIETARY, ServletConsumer);
    }

    public static final Route postPublish(ServletConsumer<WrappedServletAction> ServletConsumer) {
        return new Route(Pattern.compile("/(?<id>\\d+)/publish"), Route.PROTECTION_LEVEL.PROPRIETARY, ServletConsumer);
    }

    public static final Route postDelete(ServletConsumer<WrappedServletAction> ServletConsumer) {
        return new Route(Pattern.compile("/(?<id>\\d+)/delete"), Route.PROTECTION_LEVEL.PROPRIETARY, ServletConsumer);
    }
}
