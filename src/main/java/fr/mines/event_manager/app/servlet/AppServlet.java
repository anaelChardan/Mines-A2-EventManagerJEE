package fr.mines.event_manager.app.servlet;

import fr.mines.event_manager.core.http.Paths;
import fr.mines.event_manager.framework.router.http.Route;
import fr.mines.event_manager.framework.router.utils.WrappedServletAction;
import fr.mines.event_manager.core.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "AppServlet", urlPatterns = {"/app/*"})
public class AppServlet extends BaseServlet {
    @Override
    protected Set<Route> initGetRoutes() {
        Set<Route> routes = new HashSet<>();
        routes.add(Paths.getLogin(this::login));
        return routes;
    }

    @Override
    protected Set<Route> initPostRoutes() {
        Set<Route> routes = new HashSet<>();
        routes.add(Paths.postLogin(this::loginPost));
        return routes;
    }

    public void login(WrappedServletAction action) throws ServletException, IOException {
        this.render("login.jsp", action);

    }

    protected void loginPost(WrappedServletAction action) throws IOException, ServletException {
        if (this.connect(action.getRequest())) {
            System.out.println("Il se connecte bien");
//            action.getResponse().sendRedirect("/eventmanager/event/");
//            this.render("/event/home.jsp", action);
//            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/partials/event/home.jsp").forward(action.getRequest(),action.getResponse());
            //this.getServletContext().getNamedDispatcher("EventServlet").forward(action.getRequest(),action.getResponse());
            this.redirect(action.getResponse(),"/event/home");
            return;
        }
        System.out.println("Mauvais identifiants");
        String errorMessage = "L'adresse mail et/ou le mot de passe ne sont pas valides";
        action.getRequest().setAttribute("errorMessages", Collections.singletonMap("",errorMessage));
//        this.redirect(action.getResponse(),"/app/login");
        this.render("login.jsp", action);
    }
}