package fr.mines.event_manager.general;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import javax.servlet.*;
import javax.servlet.http.*;

public abstract class Servlet extends HttpServlet {
    protected Map<HttpWords, Map<String, Pattern>> patterns;
    protected final static String notFoundRequest = "NotFound";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.patterns = this.getSupportedPatterns();
    }

    protected Map<HttpWords, Map<String, Pattern>> getSupportedPatterns() {
        return new HashMap<HttpWords, Map<String, Pattern>>() {{
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

    protected String getMethodToCall(HttpWords method, String request) {
        Stream<Map.Entry<String, Pattern>> filteredElements = this.patterns.get(method).entrySet().stream().filter(
                e -> {
                    if (null == request)
                    {
                        return false;
                    }
                    Pattern p = e.getValue();
                    Matcher m = p.matcher(request);

                    return m.matches();
                }
        );

        Optional<Map.Entry<String, Pattern>> element = filteredElements.findFirst();
        return element.isPresent() ? element.get().getKey() : notFoundRequest;
    }

    public Pattern getPattern(HttpWords method, String action)
    {
        return this.patterns.get(method).get(action);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(HttpWords.GET, request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(HttpWords.POST, request, response);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(HttpWords.POST, request, response);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(HttpWords.POST, request, response);
    }

    protected void process(HttpWords method, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String path = request.getPathInfo();
        String methodToCall = this.getMethodToCall(method, request.getPathInfo());

        if (notFoundRequest.equals(methodToCall)) {
            PrintWriter writer = response.getWriter();
            writer.println("Ce n'est pas une url accessible");

            return;
        }

        this.introspectMethod(path, response, methodToCall);
    }

    protected Map<String, String> extractParameters(HttpWords method, String path, String methodName, String... parametersName)
    {
        Map<String, String> result = new HashMap<>();
        Pattern p = this.getPattern(HttpWords.GET, methodName);
        Matcher m = p.matcher(path);
        if (m.matches())
        {
            for(String parameter : parametersName)
            {
                result.put(parameter, m.group(parameter));
            }
        }

        return result;
    }

    protected void introspectMethod(String path, HttpServletResponse response, String method) {
        Class<?>[] argsMethodType = {String.class, HttpServletResponse.class};

        try {
            Method methode = this.getClass().getDeclaredMethod(method, argsMethodType);
            methode.setAccessible(true);
            methode.invoke(this, path, response);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}