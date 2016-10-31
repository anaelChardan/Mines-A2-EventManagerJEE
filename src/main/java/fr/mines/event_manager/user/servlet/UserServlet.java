package fr.mines.event_manager.user.servlet;

import fr.mines.event_manager.core.http.Paths;
import fr.mines.event_manager.core.utils.JSPStack;
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
        User currentUser = UserProvider.getCurrentUser(action.getRequest());
        EventManager eventManager = EventManager.getInstance();

        this.render(
                JSPStack.profile,
                action
                        .set("listFutureEvent", eventManager.getRepository().getNextOrInProgressEvents(currentUser))
                        .set("listPastEvent", eventManager.getRepository().getPastEvents(currentUser))
                        .set("listPastParticipations", eventManager.getRepository().getPastParticipations(currentUser))
        );
    }
}
