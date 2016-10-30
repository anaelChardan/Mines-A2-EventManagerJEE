package fr.mines.event_manager.home_made_framework.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.function.Function;

import static java.util.Objects.isNull;

public class SessionManager {
    public static void set(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession(false);
        if (session == null) session = request.getSession();
        session.setAttribute(key, value);
    }

    public static <T> T get(HttpServletRequest request, String key) {
        return performOperationIfNotNull(request, (a) -> {
            Object value = a.getAttribute(key);
            return isNull(value) ? null : (T) value;
        });
    }

    public static <T> T getAndRemove(HttpServletRequest request, String key)
    {
        T value = get(request, key);
        remove(request, key);
        return value;
    }

    public static void remove(HttpServletRequest request, String key) {
        performOperationIfNotNull(request, (a) -> {a.removeAttribute(key); return null;});
    }

    public static void clear(HttpServletRequest request) {
        performOperationIfNotNull(request, (a) -> {a.invalidate(); return null;});
    }

    protected static HttpSession getSession(HttpServletRequest request)
    {
        return request.getSession(false);
    }

    protected static <R> R performOperationIfNotNull(HttpServletRequest request, Function<HttpSession, R> function)
    {
        HttpSession session = getSession(request);

        if (session == null)
        {
            return null;
        }

        return function.apply(session);
    }
}
