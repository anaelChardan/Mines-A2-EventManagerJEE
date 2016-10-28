package fr.mines.event_manager.framework.router.servlet;

import fr.mines.event_manager.framework.router.http.ComputedRoute;
import fr.mines.event_manager.framework.router.http.HttpWords;
import fr.mines.event_manager.framework.router.utils.WrappedServletAction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        Optional<ComputedRoute> entry = this.getMethodToCallWithParameters(method, path);

        if (!entry.isPresent()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);

            return;
        }

        ComputedRoute computedRoute = entry.get();

        WrappedServletAction action = new WrappedServletAction(request,response,computedRoute.getParameters());
        switch (computedRoute.getProtectionLevel())
        {
            case NONE:
                computedRoute.consume(action);
                break;
            case CONNECTED:
                this.redirectConnectedProtectionRoute(action,computedRoute);
                break;
            case PROPRIETARY:
                this.redirectProprietaryProtectionRoute(action,computedRoute);
                break;
        }
    }

    protected abstract void redirectConnectedProtectionRoute(WrappedServletAction action, ComputedRoute route) throws IOException, ServletException;

    protected abstract void redirectProprietaryProtectionRoute(WrappedServletAction action, ComputedRoute route);
}