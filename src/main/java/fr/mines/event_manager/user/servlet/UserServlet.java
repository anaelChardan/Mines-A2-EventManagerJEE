package fr.mines.event_manager.user.servlet;


import fr.mines.event_manager.app.repository.TankRepository;
import fr.mines.event_manager.core.http.Paths;
import fr.mines.event_manager.framework.router.http.Route;
import fr.mines.event_manager.core.servlet.BaseServlet;
import fr.mines.event_manager.framework.router.utils.UtilException;
import fr.mines.event_manager.framework.router.utils.WrappedServletAction;
import fr.mines.event_manager.framework.security.UserProvider;
import fr.mines.event_manager.user.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

@WebServlet(name = "UserServlet", urlPatterns = {"/user/*"})
public class UserServlet extends BaseServlet {
    @Override
    protected Set<Route> initGetRoutes() {
        Set<Route> routes = new HashSet<>();

        routes.add(Paths.getProfile(UtilException.rethrowConsumer(this::profile)));

        return routes;
    }

    protected void profile(WrappedServletAction action) throws IOException, ServletException {
//        System.out.println("coucou");
        action.getRequest().setAttribute("user", UserProvider.getCurrentUser(action.getRequest()));
        this.render("user/consultUser.jsp", action.getRequest(), action.getResponse());
    }
}
