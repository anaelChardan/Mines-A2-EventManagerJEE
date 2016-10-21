package fr.mines.event_manager.framework.router.servlet;

import fr.mines.event_manager.framework.router.http.HttpWords;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;

public abstract class Servlet extends RoutedServlet {
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

    protected void process(HttpWords method, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        Optional<AbstractMap.SimpleEntry<String, Map<String, String>>> entry = this.getMethodToCallWithParameters(method, path);

        if (!entry.isPresent()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);

            return;
        }

        this.introspectMethod(entry.get().getKey(), request, response, entry.get().getValue());
    }
}