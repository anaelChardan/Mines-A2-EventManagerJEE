package fr.mines.event_manager.framework.router.servlet;

import fr.mines.event_manager.framework.router.http.ComputedRoute;
import fr.mines.event_manager.framework.router.http.HttpWords;
import fr.mines.event_manager.framework.router.http.Route;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RoutedServlet extends HttpServlet {
    protected Map<HttpWords, Set<Route>> patterns = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        this.patterns = new HashMap<HttpWords, Set<Route>>() {{
            put(HttpWords.GET, initGetRoutes());
            put(HttpWords.POST, initPostRoutes());
            put(HttpWords.PUT, initPutRoutes());
            put(HttpWords.DELETE, initDeleteRoutes());
        }};
    }

    protected Set<Route> initGetRoutes() {
        return Collections.emptySet();
    }

    protected Set<Route> initPostRoutes() {
        return Collections.emptySet();
    }

    protected Set<Route>initPutRoutes() {
        return Collections.emptySet();
    }

    protected Set<Route> initDeleteRoutes() {
        return Collections.emptySet();
    }

    /**
     * Method to call from pattern
     *
     * Route, (parametersName, parameterValue)
     * @param method
     * @param request
     * @return The method
     */
    protected Optional<ComputedRoute> getMethodToCallWithParameters(HttpWords method, String request) {

        String path = (request != null) ? request : "/";

        return patterns
                .get(method)
                .stream()
                .filter(e -> (e.getPattern().matcher(path).matches()))
                .map(e -> {
                    ComputedRoute computedRoute = new ComputedRoute(e.getMethod(), e.getProtectionLevel());
                    Pattern p = e.getPattern();
                    Matcher m = p.matcher(path);
                    m.matches();
                    e.getNamedGroups().forEach(groupName -> computedRoute.add(groupName, m.group(groupName)));

                    return computedRoute;
                })
                .findFirst();
    }

    protected void introspectMethod(ComputedRoute computedRoute, HttpServletRequest request, HttpServletResponse response) throws IOException {

        Class<?>[] argsClasses = {HttpServletRequest.class, HttpServletResponse.class, Map.class};
        Object[] args = {request, response, computedRoute};

        if (!proceedToIntrospect(computedRoute.getMethodName(), argsClasses, computedRoute.getParameters())) {

            Class<?>[] argsClasses1 = {HttpServletRequest.class, HttpServletResponse.class};
            Object[] args1 = {request, response};

            if (!proceedToIntrospect(computedRoute.getMethodName(), argsClasses1, args1)) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    private boolean proceedToIntrospect(String methodName, Class<?>[] argsClasses, Object... args) {
        boolean success = true;

        try {
            Method method = this.getClass().getDeclaredMethod(methodName, argsClasses);
            method.setAccessible(true);
            method.invoke(this, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            success = false;
        }

        return success;
    }
}
