package fr.mines.event_manager.event.servlet;

import fr.mines.event_manager.core.http.Paths;
import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.framework.router.http.Route;
import fr.mines.event_manager.core.servlet.BaseServlet;
import fr.mines.event_manager.framework.router.utils.WrappedServletAction;
import fr.mines.event_manager.event.manager.EventManager;
import fr.mines.event_manager.framework.security.UserProvider;
import fr.mines.event_manager.framework.validator.ValidatorProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "EventServlet", urlPatterns = {"/event/*"})
public class EventServlet extends BaseServlet {
    @Override
    protected Set<Route> initGetRoutes() {
        Set<Route> routes = new HashSet<>();

        routes.add(Paths.getIndexEvent(this::index));
        routes.add(Paths.getOneEvent(this::showOne));
        routes.add(Paths.getCreateEvent(this::newEventForm));

        return routes;
    }

    @Override
    protected Set<Route> initPostRoutes() {
        Set<Route> routes = new HashSet<>();
        routes.add(Paths.postCreateEvent(this::eventPost));
        return routes;
    }

    //
    protected void index(WrappedServletAction action) throws IOException {
        PrintWriter out = action.getResponse().getWriter();
        System.out.println("coucou maggle");
    }

    protected void showOne(WrappedServletAction action) throws IOException, ServletException {
        Optional<Event> eventOptional = EventManager.getInstance().find(Integer.parseInt(action.getParameters().get("id")));

        if (!eventOptional.isPresent()) {
            this.redirect(action.getResponse(), "/event");
            return;
        }

        Event event = eventOptional.get();
        action.getRequest().setAttribute("event", event);
        action.getRequest().setAttribute("isSubscribable", event.isSubscribable(UserProvider.getCurrentUser(action.getRequest())));
        this.render("/event/", action);
    }

    protected void newEventForm(WrappedServletAction action) throws ServletException, IOException {
        HttpSession session = action.getRequest().getSession(false);
        if (null == session) {
            this.render("login.jsp", action);
            return;
        }
        this.render("/event/create.jsp", action);
    }

    protected void eventPost(WrappedServletAction action) throws ServletException, IOException {
        HttpSession session = action.getRequest().getSession(false);
        if (null == session) {
            this.render("login.jsp", action);
            return;
        }

        Event event = EventManager.getInstance().create(action.getRequest());
        Map<String, String> errors = ValidatorProcessor.getInstance().isValid(event);
        action.getRequest().setAttribute("errorMessages", errors);
        if (!errors.isEmpty()) {
            action.getRequest().setAttribute("event", event);
            this.render("/event/create.jsp", action);
            return;
        }

        this.redirect(action.getResponse(), "/event/" + EventManager.getInstance().persist(event).getId());
    }

}
