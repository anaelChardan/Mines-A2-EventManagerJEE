package fr.mines.event_manager.user.servlet;

import fr.mines.event_manager.core.http.Paths;
import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.event.manager.EventManager;
import fr.mines.event_manager.home_made_framework.router.http.Route;
import fr.mines.event_manager.core.servlet.BaseServlet;
import fr.mines.event_manager.home_made_framework.router.utils.WrappedServletAction;
import fr.mines.event_manager.home_made_framework.security.UserProvider;
import fr.mines.event_manager.user.entity.User;

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
        User currentUser = UserProvider.getCurrentUser(action.getRequest());

        List<Event> listFutureEvent = EventManager.getInstance().getRepository().getNextOrInProgressEvents(currentUser);
        List<Event> listPastEvent = EventManager.getInstance().getRepository().getPastEvents(currentUser);
        List<Event> listPastParticipations = EventManager.getInstance().getRepository().getPastParticipations(currentUser);


        action.getRequest().setAttribute("listFutureEvent", listFutureEvent);
        action.getRequest().setAttribute("listPastEvent", listPastEvent);
        action.getRequest().setAttribute("listPastParticipations", listPastParticipations);
        this.render("user/profile.jsp", action);

    }
}
