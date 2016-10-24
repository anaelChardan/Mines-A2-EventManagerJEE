package fr.mines.event_manager.app.servlet;

import fr.mines.event_manager.core.http.Paths;
import fr.mines.event_manager.framework.router.http.Route;
import fr.mines.event_manager.framework.router.utils.UtilException;
import fr.mines.event_manager.framework.router.utils.WrappedServletAction;
import fr.mines.event_manager.framework.security.UserProvider;
import fr.mines.event_manager.core.servlet.BaseServlet;

import javax.naming.NameParser;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

@WebServlet(name = "AppServlet", urlPatterns = {"/app/*"})
public class AppServlet extends BaseServlet {
    @Override
    protected Set<Route> initGetRoutes() {
        Set<Route> routes = new HashSet<>();
        routes.add(Paths.getLogin(UtilException.rethrowConsumer(this::login)));
        return routes;
    }

    @Override
    protected Set<Route> initPostRoutes() {
        Set<Route> routes = new HashSet<>();
        routes.add(Paths.postLogin(UtilException.rethrowConsumer(this::loginPost)));
        return routes;
    }

    public void login(WrappedServletAction action) throws ServletException, IOException {
        this.render("login.jsp", action.getRequest(), action.getResponse());

    }

    protected void loginPost(WrappedServletAction action) throws IOException, ServletException {
        if (this.connect(action.getRequest())) {
           this.render("home.jsp", action.getRequest(), action.getResponse());
            return;
        }

        String errorMessage = "L'adresse mail et/ou le mot de passe ne sont pas valides";
        action.getRequest().setAttribute("errorMessage", errorMessage);
        this.render("login.jsp", action.getRequest(), action.getResponse());
    }
}
