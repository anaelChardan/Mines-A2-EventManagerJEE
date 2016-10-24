package fr.mines.event_manager.home.servlet;

import fr.mines.event_manager.core.http.Paths;
import fr.mines.event_manager.framework.router.http.Route;
import fr.mines.event_manager.core.servlet.BaseServlet;
import fr.mines.event_manager.framework.router.utils.UtilException;
import fr.mines.event_manager.framework.router.utils.WrappedServletAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@WebServlet(name = "HomeServlet", urlPatterns = {"/"})
public class HomeServlet extends BaseServlet {
    @Override
    protected Set<Route> initGetRoutes() {
        Set<Route> routes = new HashSet<>();
        routes.add(Paths.getHome(UtilException.rethrowConsumer(this::home)));
        return routes;
    }

    protected void home(WrappedServletAction action) throws IOException, ServletException {
        this.render("login.jsp", action.getRequest(), action.getResponse());
    }
}
