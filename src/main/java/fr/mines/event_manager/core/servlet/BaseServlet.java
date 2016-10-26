package fr.mines.event_manager.core.servlet;

import fr.mines.event_manager.app.repository.TankRepository;
import fr.mines.event_manager.framework.entity.AbstractUser;
import fr.mines.event_manager.framework.repository.utils.Field;
import fr.mines.event_manager.framework.router.http.ComputedRoute;
import fr.mines.event_manager.framework.router.http.HttpWords;
import fr.mines.event_manager.framework.router.servlet.Servlet;
import fr.mines.event_manager.framework.router.utils.WrappedServletAction;
import fr.mines.event_manager.framework.security.UserProvider;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseServlet extends Servlet {
    protected void render(String jspPage, WrappedServletAction action) throws ServletException, IOException {
        action.getRequest().setAttribute("jspPage", jspPage);
        action.getRequest().setAttribute("IS_LOGGED", UserProvider.isConnected(action.getRequest()));
        action.getRequest().setAttribute("CURRENT_USER", UserProvider.getCurrentUser(action.getRequest()));

        getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(action.getRequest(), action.getResponse());
    }

    @Override
    protected void redirectConnectedProtectionRoute(HttpWords method, HttpServletRequest request, HttpServletResponse response, ComputedRoute route) throws IOException {
//        ComputedRoute homeRoute = new ComputedRoute(Paths.getHome.getMethod(), route.getProtectionLevel());
//        homeRoute.add("alertMessage", "Vous devez être connecté pour accéder à l'application");
////        response.sendRedirect();
//        this.introspectMethod(homeRoute, request, response);
    }

    @Override
    protected void redirectProprietaryProtectionRoute(HttpWords method, HttpServletRequest request, HttpServletResponse response, ComputedRoute route) {

    }

    protected boolean connect(HttpServletRequest request)
    {
        return UserProvider.connect(request, () -> TankRepository.getInstance().getUserRepository().findSingleBy(
                new Field<String>("email", request.getParameter("email"), Field.Filter.EQUAL),
                new Field<String>("password", request.getParameter("password"), Field.Filter.EQUAL)
            ).map(AbstractUser.class::cast)
        );
    }

    protected void redirect(HttpServletResponse response, String endPoint) throws IOException {
        response.sendRedirect(this.getServletContext().getContextPath() + endPoint);
    }
}
