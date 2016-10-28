package fr.mines.event_manager.event.servlet;

import fr.mines.event_manager.core.http.Paths;
import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.framework.router.http.Route;
import fr.mines.event_manager.core.servlet.BaseServlet;
import fr.mines.event_manager.framework.router.utils.WrappedServletAction;
import fr.mines.event_manager.event.manager.EventManager;
import fr.mines.event_manager.framework.security.UserProvider;
import fr.mines.event_manager.framework.validator.ValidatorProcessor;
import fr.mines.event_manager.user.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

@WebServlet(name = "EventServlet", urlPatterns = {"/event/*"})
public class EventServlet extends BaseServlet {
    EventManager manager = EventManager.getInstance();

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
        routes.add(Paths.postSubscribeToEvent(this::subscribeToEvent));

        return routes;
    }

    protected void index(WrappedServletAction action) throws IOException, ServletException {
        User currentUser = UserProvider.getCurrentUser(action.getRequest());

        List<Event> eventsSubscribed = manager.getRepository().getSubscribedEventsByUserSortedByDate(currentUser);
        List<Event> eventsNotPassed = manager.getRepository().getEventsNotPassed();
        List<Event> eventsPassed = manager.getRepository().getEventsPassed();

        action.getRequest().setAttribute("eventsSubscribed", eventsSubscribed);
        action.getRequest().setAttribute("eventsNotPassed",eventsNotPassed);
        action.getRequest().setAttribute("eventsPassed",eventsPassed);

        this.render("/event/home.jsp", action);
    }

    protected void subscribeToEvent(WrappedServletAction action) throws IOException {
        Integer id = Integer.parseInt(action.getParameters().get("id"));
        manager.addUserToEvent(UserProvider.getCurrentUser(action.getRequest()), id);
        this.redirect(action.getResponse(), "/event/" + id);
    }

    protected void showOne(WrappedServletAction action) throws IOException, ServletException {
        Optional<Event> eventOptional = manager.find(Integer.parseInt(action.getParameters().get("id")));

        if (!eventOptional.isPresent()) {
            this.redirect(action.getResponse(), "/event");
            return;
        }

        Event event = eventOptional.get();
        action.getRequest().setAttribute("event", event);
        action.getRequest().setAttribute("isSubscribable", event.isSubscribable(UserProvider.getCurrentUser(action.getRequest())));

        this.render("/event/full.jsp", action);
    }

    protected void newEventForm(WrappedServletAction action) throws ServletException, IOException {
        this.render("/event/create.jsp", action);
    }

    protected void eventPost(WrappedServletAction action) throws ServletException, IOException {
        Event event = manager.create(action.getRequest());

        Map<String, String> errors = ValidatorProcessor.getInstance().isValid(event);
        action.getRequest().setAttribute("errorMessages", errors);
        if (!errors.isEmpty()) {
            action.getRequest().setAttribute("event", event);
            this.render("/event/create.jsp", action);
            return;
        }

        this.redirect(action.getResponse(), "/event/" + manager.persist(event).getId());
    }

}
