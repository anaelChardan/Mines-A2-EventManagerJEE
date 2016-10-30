package fr.mines.event_manager.user.servlet;

import fr.mines.event_manager.core.http.Paths;
import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.event.manager.EventManager;
import fr.mines.event_manager.framework.router.http.Route;
import fr.mines.event_manager.core.servlet.BaseServlet;
import fr.mines.event_manager.framework.router.utils.WrappedServletAction;
import fr.mines.event_manager.framework.security.UserProvider;
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
        User usr = UserProvider.getCurrentUser(action.getRequest());
        List<Event> listPastEvent = EventManager.getInstance().getRepository().getSubscribedEventsByUserSortedByDateBeforeNow(usr);
        List<Event> listFutureEvent = EventManager.getInstance().getRepository().getSubscribedEventsByUserSortedByDateAfterNow(usr);
        Map<String, List<Event>> listAuthoredEvent = EventManager.getInstance().getEventsCreatedByUserSortedByDate(usr);

        action.getRequest().setAttribute("user", usr);
        action.getRequest().setAttribute("pastEventAuthored", listAuthoredEvent.get("past"));
        action.getRequest().setAttribute("futureEventAuthored", listAuthoredEvent.get("future"));
        action.getRequest().setAttribute("pastEventSubscribed", listPastEvent);
        action.getRequest().setAttribute("futureEventSubscribed", listFutureEvent);
        this.render("user/consult.jsp", action);

    }
}
