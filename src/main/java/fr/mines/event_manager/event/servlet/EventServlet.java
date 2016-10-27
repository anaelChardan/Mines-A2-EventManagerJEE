package fr.mines.event_manager.event.servlet;

import fr.mines.event_manager.core.http.Paths;
import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.framework.router.http.Route;
import fr.mines.event_manager.core.servlet.BaseServlet;
import fr.mines.event_manager.framework.router.utils.WrappedServletAction;
import fr.mines.event_manager.event.manager.EventManager;
import fr.mines.event_manager.framework.security.UserProvider;
import fr.mines.event_manager.framework.utils.Alert;
import fr.mines.event_manager.framework.validator.ValidatorProcessor;
import fr.mines.event_manager.user.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        routes.add(Paths.getEditEvent(this::edit));
        return routes;
    }

    @Override
    protected Set<Route> initPostRoutes() {
        Set<Route> routes = new HashSet<>();

        routes.add(Paths.postCreateEvent(this::eventPost));
        routes.add(Paths.postSubscribeToEvent(this::subscribeToEvent));
        routes.add(Paths.postEditEvent(this::editPost));
        routes.add(Paths.postActionEvent(this::actionEvent));

        return routes;
    }

    protected void index(WrappedServletAction action) throws IOException, ServletException {
        User currentUser = UserProvider.getCurrentUser(action.getRequest());

        List<Event> eventsSubscribed = manager.getRepository().getSubscribedEventsByUserSortedByDate(currentUser);
        List<Event> eventsNotPassed = manager.getRepository().getEventsNotPassed();
        List<Event> eventsPassed = manager.getRepository().getEventsPassed();

        action.getRequest().setAttribute("eventsSubscribed", eventsSubscribed);
        action.getRequest().setAttribute("eventsNotPassed", eventsNotPassed);
        action.getRequest().setAttribute("eventsPassed", eventsPassed);

        this.render("/event/home.jsp", action);
    }

    protected void subscribeToEvent(WrappedServletAction action) throws IOException {
        Integer id = Integer.parseInt(action.getParameters().get("id"));
        manager.addUserToEvent(UserProvider.getCurrentUser(action.getRequest()), id);
        this.redirect(action, "/event/" + id, new Alert(Alert.TYPE.SUCCESS, "Vous êtes bien inscrit à l'évènement"));
    }

    protected void showOne(WrappedServletAction action) throws IOException, ServletException {
        Optional<Event> eventOptional = manager.find(Integer.parseInt(action.getParameters().get("id")));

        if (!eventOptional.isPresent()) {
            this.redirect(action, "/event");
            return;
        }

        User usr = UserProvider.getCurrentUser(action.getRequest());

        Event event = eventOptional.get();
        action.getRequest().setAttribute("event", event);
        action.getRequest().setAttribute("isSubscribable", event.isSubscribable(UserProvider.getCurrentUser(action.getRequest())));
        action.getRequest().setAttribute("userConnected", usr);

        this.render("/event/full.jsp", action);
    }

    protected void newEventForm(WrappedServletAction action) throws ServletException, IOException {
        this.render("/event/create.jsp", action);
    }

    protected void eventPost(WrappedServletAction action) throws ServletException, IOException {
        Event event = manager.create(action.getRequest());

        Map<String, String> errors = ValidatorProcessor.getInstance().isValid(event);

        if (!errors.isEmpty()) {
            action.getRequest().setAttribute("event", event);
            this.render("/event/create.jsp", action, new Alert(Alert.TYPE.DANGER, errors));
            return;
        }
        this.redirect(action, "/event/" + manager.persist(event).getId(), new Alert(Alert.TYPE.SUCCESS, "Votre évènement a bien été crée"));
    }

    protected void edit(WrappedServletAction action) throws ServletException, IOException {
        this.render("/event/edit.jsp", action);
    }

    protected void editPost(WrappedServletAction action) throws ServletException, IOException {
        Event event = manager.create(action.getRequest());
        Map<String, String> errors = ValidatorProcessor.getInstance().isValid(event);

        if (!errors.isEmpty()) {
            action.getRequest().setAttribute("event", event);
            this.render("/event/edit.jsp", action, new Alert(Alert.TYPE.DANGER, errors));
            return;
        }

        this.redirect(action, "/event/" + manager.update(event).getId(), new Alert(Alert.TYPE.SUCCESS, "Votre évènement a bien été édité"));
    }

   protected void actionEvent(WrappedServletAction action) throws ServletException, IOException {
        HttpSession session = action.getRequest().getSession(false);
        if (null == session) {
            this.render("login.jsp", action);
            return;
        }

        String act = action.getRequest().getParameter("action");
        if(act.equals("cancel")){
            manager.getRepository().delete(Integer.parseInt(action.getParameters().get("id")));
            this.render("/event/index.jsp", action);
        }
        if(act.equals("subscribe")){

        }
        if(act.equals("publish")){

        }
        if(act.equals("modify")){

        }

    }
}
