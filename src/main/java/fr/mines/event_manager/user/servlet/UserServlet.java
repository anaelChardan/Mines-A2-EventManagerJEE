package fr.mines.event_manager.user.servlet;

import fr.mines.event_manager.core.http.Paths;
import fr.mines.event_manager.framework.router.http.Route;
import fr.mines.event_manager.core.servlet.BaseServlet;
import fr.mines.event_manager.framework.router.utils.WrappedServletAction;
import fr.mines.event_manager.framework.security.UserProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "UserServlet", urlPatterns = {"/user/*"})
public class UserServlet extends BaseServlet {
    @Override
    protected Set<Route> initGetRoutes() {
        Set<Route> routes = new HashSet<>();

        routes.add(Paths.getProfile(this::profile));

        return routes;
    }

    protected void profile(WrappedServletAction action) throws IOException, ServletException {
        action.getRequest().setAttribute("user", UserProvider.getCurrentUser(action.getRequest()));
        this.render("user/consultUser.jsp", action.getRequest(), action.getResponse());
    }
}
