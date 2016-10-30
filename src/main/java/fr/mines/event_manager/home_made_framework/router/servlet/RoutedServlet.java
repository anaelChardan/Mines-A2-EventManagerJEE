package fr.mines.event_manager.home_made_framework.router.servlet;

import fr.mines.event_manager.home_made_framework.router.http.ComputedRoute;
import fr.mines.event_manager.home_made_framework.router.http.HttpWords;
import fr.mines.event_manager.home_made_framework.router.http.Route;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
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
        }};
    }

    protected Set<Route> initGetRoutes() {
        return Collections.emptySet();
    }

    protected Set<Route> initPostRoutes() {
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
    protected Optional<ComputedRoute> getMethodToCallWithParameters(HttpWords method, String path) {
        return patterns
                .get(method)
                .stream()
                .filter(e -> (e.getPattern().matcher(path).matches()))
                .map(e -> {
                    ComputedRoute computedRoute = new ComputedRoute(e.getProtectionLevel(), e.getConsumer());
                    Pattern p = e.getPattern();
                    Matcher m = p.matcher(path);
                    m.matches();
                    e.getNamedGroups().forEach(groupName -> computedRoute.add(groupName, m.group(groupName)));

                    return computedRoute;
                })
                .findFirst();
    }
}
