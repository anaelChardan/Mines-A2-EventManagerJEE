package fr.mines.event_manager.app.servlet;

import fr.mines.event_manager.framework.router.http.Route;
import fr.mines.event_manager.framework.security.UserProvider;
import fr.mines.event_manager.core.servlet.BaseServlet;

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
        return new HashSet<Route>() {{
            add(new Route("login", Pattern.compile("/login"), Route.PROTECTION_LEVEL.NONE ));
        }};
    }

    @Override
    protected Set<Route> initPostRoutes() {
        return new HashSet<Route>() {{
            add(new Route("loginPost", Pattern.compile("/loginpost"), Route.PROTECTION_LEVEL.NONE));
        }};
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.render("login.jsp", request, response);

    }

    protected void loginPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (this.connect(request)) {
           this.render("home.jsp", request, response);
            return;
        }

        String errorMessage = "L'adresse mail et/ou le mot de passe ne sont pas valides";
        request.setAttribute("errorMessage", errorMessage);
        this.render("login.jsp", request, response);
    }


}
