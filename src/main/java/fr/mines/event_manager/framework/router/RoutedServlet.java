package fr.mines.event_manager.framework.router;

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
    protected Map<HttpWords, Map<String, Pattern>> patterns = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        this.patterns = new HashMap<HttpWords, Map<String, Pattern>>() {{
            put(HttpWords.GET, initGetRoutes());
            put(HttpWords.POST, initPostRoutes());
            put(HttpWords.PUT, initPutRoutes());
            put(HttpWords.DELETE, initDeleteRoutes());
        }};
    }

    protected Map<String, Pattern> initGetRoutes() {
        return Collections.emptyMap();
    }

    protected Map<String, Pattern> initPostRoutes() {
        return Collections.emptyMap();
    }

    protected Map<String, Pattern> initPutRoutes() {
        return Collections.emptyMap();
    }

    protected Map<String, Pattern> initDeleteRoutes() {
        return Collections.emptyMap();
    }

    /**
     * Method to call from pattern
     *
     * @param method
     * @param request
     * @return The method
     */
    protected Optional<AbstractMap.SimpleEntry<String, Map<String, String>>> getMethodToCallWithParameters(HttpWords method, String request) {

        String path = (request != null) ? request : "/";

        return patterns
                .get(method)
                .entrySet()
                .stream()
                .filter(e -> (e.getValue().matcher(path).matches()))
                .map(e -> {
                    Pattern p = e.getValue();
                    Matcher m = p.matcher(path);
                    m.matches();
                    Map<String, String> parameters = new HashMap<>();
                    this.getNamedGroups(p.pattern()).forEach(groupName -> parameters.put(groupName, m.group(groupName)));

                    return new AbstractMap.SimpleEntry<>(e.getKey(), parameters);
                })
                .findFirst();
    }

    protected Set<String> getNamedGroups(String regex) {
        Set<String> namedGroups = new TreeSet<>();
        Matcher m = Pattern.compile("\\(\\?<([a-zA-Z][a-zA-Z0-9]*)>").matcher(regex);

        while (m.find()) {
            namedGroups.add(m.group(1));
        }

        return namedGroups;
    }

    protected void introspectMethod(String methodName, HttpServletRequest request, HttpServletResponse response, Map<String, String> parameters) throws IOException {

        Class<?>[] argsClasses = {HttpServletRequest.class, HttpServletResponse.class, Map.class};
        Object[] args = {request, response, parameters};

        if (!proceedToIntrospect(methodName, argsClasses, args)) {

            Class<?>[] argsClasses1 = {HttpServletRequest.class, HttpServletResponse.class};
            Object[] args1 = {request, response};

            if (!proceedToIntrospect(methodName, argsClasses1, args1)) {
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
