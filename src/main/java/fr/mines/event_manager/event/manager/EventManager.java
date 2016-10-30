package fr.mines.event_manager.event.manager;

import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.event.repository.EventRepository;
import fr.mines.event_manager.framework.manager.BaseEntityManager;
import fr.mines.event_manager.framework.security.UserProvider;
import fr.mines.event_manager.user.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Damien on 24/10/2016.
 */
public class EventManager implements BaseEntityManager<Event> {

    private static EventManager instance = null;
    private EventRepository repository = new EventRepository();

    private EventManager() {
    }

    public static synchronized EventManager getInstance() {
        if (null == instance) {
            instance = new EventManager();
        }

        return instance;
    }

    public Event getBaseObject(HttpServletRequest request, Event event) {
        // Gestion des dates
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = request.getParameter("start_date").isEmpty() ? null : sdf.parse(request.getParameter("start_date"));
            endDate = request.getParameter("end_date").isEmpty() ? null : sdf.parse(request.getParameter("end_date"));
        } catch (ParseException e) {
        }

        Integer maxTickets = request.getParameter("max_tickets").isEmpty() ? null : Integer.parseInt(request.getParameter("max_tickets"));
        Double price = request.getParameter("price").isEmpty() ? null : Double.parseDouble(request.getParameter("price"));

        return event
                .setAddress(AddressManager.getInstance().create(request))
                .setAuthor(UserProvider.getCurrentUser(request))
                .setDescription(request.getParameter("description"))
                .setMaxTickets(maxTickets)
                .setName(request.getParameter("titre"))
                .setPrice(price)
                .setStartDate(startDate)
                .setEndDate(endDate);
    }

    @Override
    public Event create(HttpServletRequest request) {
        return this.getBaseObject(request, new Event()).setPublished("create-and-publish".equals(request.getParameter("action")));
    }

    public Event edit(HttpServletRequest request, Event event) {
        return this.getBaseObject(request, event).setPublished("edit-and-publish".equals(request.getParameter("action")));
    }

    @Override
    public Event persist(Event object) {
        return repository.create(object);
    }

    public Event update(Event object) {
        return repository.update(object);
    }

    public Optional<Event> find(int id) {
        return repository.find(id);
    }

    public EventRepository getRepository() {
        return this.repository;
    }

    public void addUserToEvent(User currentUser, int id) {
        Optional<Event> eventOptionnal = repository.find(id);

        if (!eventOptionnal.isPresent()) {
            return;
        }

        Event event = eventOptionnal.get();

        if (!event.isSubscribable(currentUser)) {
            return;
        }

        repository.update(event.addSubscribers(currentUser));
    }

    public void removeUserToEvent(User currentUser, int id) {
        Optional<Event> eventOptionnal = repository.find(id);

        if (!eventOptionnal.isPresent()) {
            return;
        }

        Event event = eventOptionnal.get();

        if (!event.isASubscriber(currentUser)) {
            return;
        }

        repository.update(event.removeSubscriber(currentUser));
    }

    public boolean delete(Event event) {
        return this.repository.delete(event);
    }
}
