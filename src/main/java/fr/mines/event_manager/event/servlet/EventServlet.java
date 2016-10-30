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
import java.io.IOException;
import java.util.*;

@WebServlet(name = "EventServlet", urlPatterns = {"/event/*"})
public class EventServlet extends BaseServlet {

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
        routes.add(Paths.postActionEvent(this::actionEvent));
        routes.add(Paths.postEditEvent(this::editPost));
        routes.add(Paths.postSubscribeToEvent(this::subscribeToEvent));
        routes.add(Paths.postUnsubscribeToEvent(this::unsubscribeToEvent));

        return routes;
    }

    protected void index(WrappedServletAction action) throws IOException, ServletException {
        User currentUser = UserProvider.getCurrentUser(action.getRequest());

        List<Event> eventsInProgressSubscribed = EventManager.getInstance().getRepository().getNextOrInProgressParticipations(currentUser,true);
        List<Event> eventsInProgressNotSubscribed = EventManager.getInstance().getRepository().getNextOrInProgressParticipations(currentUser,false);
        List<Event> eventsPassed = EventManager.getInstance().getRepository().getEventsPassed(currentUser);

        action.getRequest().setAttribute("eventsInProgressSubscribed", eventsInProgressSubscribed);
        action.getRequest().setAttribute("eventsInProgressNotSubscribed", eventsInProgressNotSubscribed);
        action.getRequest().setAttribute("eventsPassed", eventsPassed);

        this.render("/event/home.jsp", action);
    }

    protected void showOne(WrappedServletAction action) throws IOException, ServletException {
        Optional<Event> eventOptional = EventManager.getInstance().find(Integer.parseInt(action.getParameters().get("id")));

        if (!eventOptional.isPresent()) {
            this.redirect(action, "/event", new Alert(Alert.TYPE.DANGER, "L'événement que vous voulez consulter n'existe pas"));
            return;
        }

        action.getRequest().setAttribute("event", eventOptional.get());
        action.getRequest().setAttribute("isSubscribable", eventOptional.get().isSubscribable(UserProvider.getCurrentUser(action.getRequest())));
        action.getRequest().setAttribute("isSubscriber",eventOptional.get().isASubscriber(UserProvider.getCurrentUser(action.getRequest())));
        this.render("/event/full.jsp", action);
    }

    protected void newEventForm(WrappedServletAction action) throws ServletException, IOException {
        this.render("/event/create.jsp", action);
    }

    protected void eventPost(WrappedServletAction action) throws ServletException, IOException {
        Event event = EventManager.getInstance().create(action.getRequest());

        Map<String, String> errors = ValidatorProcessor.getInstance().isValid(event);

        if (!errors.isEmpty()) {
            action.getRequest().setAttribute("event", event);
            this.render("/event/create.jsp", action, new Alert(Alert.TYPE.DANGER, errors));
            return;
        }
        this.redirect(action, "/event/" + EventManager.getInstance().persist(event).getId(), new Alert(Alert.TYPE.SUCCESS, "Votre évènement a bien été créé"));
    }

    protected void edit(WrappedServletAction action) throws ServletException, IOException {
        this.render("/event/edit.jsp", action);
    }

    protected void editPost(WrappedServletAction action) throws ServletException, IOException {
        Event event = EventManager.getInstance().create(action.getRequest());
        Map<String, String> errors = ValidatorProcessor.getInstance().isValid(event);

        if (!errors.isEmpty()) {
            action.getRequest().setAttribute("event", event);
            this.render("/event/edit.jsp", action, new Alert(Alert.TYPE.DANGER, errors));
            return;
        }

        this.redirect(action, "/event/" + EventManager.getInstance().update(event).getId(), new Alert(Alert.TYPE.SUCCESS, "Votre évènement a bien été édité"));
    }

    protected void subscribeToEvent(WrappedServletAction action) throws IOException {
        Integer id = Integer.parseInt(action.getParameters().get("id"));
        EventManager.getInstance().addUserToEvent(UserProvider.getCurrentUser(action.getRequest()), id);
        this.redirect(action, "/event/" + id, new Alert(Alert.TYPE.SUCCESS, "Vous êtes bien inscrit à l'évènement"));
    }

    protected void unsubscribeToEvent(WrappedServletAction action) throws IOException {
        Integer id = Integer.parseInt(action.getParameters().get("id"));
        EventManager.getInstance().removeUserToEvent(UserProvider.getCurrentUser(action.getRequest()), id);
        this.redirect(action, "/event/" + id, new Alert(Alert.TYPE.SUCCESS, "Vous êtes bien désinscrit de l'évènement"));
    }


   protected void actionEvent(WrappedServletAction action) throws ServletException, IOException {

        String act = action.getRequest().getParameter("action");
        Integer eventId = Integer.parseInt(action.getParameters().get("id"));
        Event event = EventManager.getInstance().find(eventId).get();

       if(act.equals("cancel")){
           EventManager.getInstance().getRepository().delete(eventId);
            this.render("/event/index.jsp", action);
        }
        if(act.equals("subscribe")){
            EventManager.getInstance().addUserToEvent(UserProvider.getCurrentUser(action.getRequest()),eventId);
            this.render("/event/full.jsp", action);
        }
        if(act.equals("publish")){
            event.setPublished(true);
            EventManager.getInstance().update(event);
            this.render("/event/full.jsp", action);
        }
        if(act.equals("modify")){
            this.render("/event/edit.jsp", action);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        EventManager.getInstance().close();
    }
}
