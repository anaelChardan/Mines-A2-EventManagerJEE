package fr.mines.event_manager.event.manager;

import fr.mines.event_manager.app.repository.TankRepository;
import fr.mines.event_manager.event.entity.Address;
import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.event.repository.EventRepository;
import fr.mines.event_manager.framework.manager.BaseEntityManager;
import fr.mines.event_manager.framework.repository.BaseEntityManagerWrapper;
import fr.mines.event_manager.framework.security.UserProvider;
import fr.mines.event_manager.user.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * Created by Damien on 24/10/2016.
 */
public class EventManager implements BaseEntityManager<Event> {

    private static EventManager instance = null;
    private EventRepository repository = TankRepository.getInstance().getEventRepository();

    private EventManager()
    {}

    public static EventManager getInstance()
    {
        if (null == instance)
        {
            instance = new EventManager();
        }

        return instance;
    }

    @Override
    public Event create(HttpServletRequest request) {
        // Gestion des dates
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = sdf.parse(request.getParameter("start_date"));
            endDate = sdf.parse(request.getParameter("end_date"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (new Event())
                .setAddress(AddressManager.getInstance().create(request))
                .setAuthor(UserProvider.getCurrentUser(request))
                .setDescription(request.getParameter("description"))
                .setMaxTickets(Integer.parseInt(request.getParameter("max_tickets")))
                .setName(request.getParameter("titre"))
                .setPrice(Double.parseDouble(request.getParameter("price")))
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setPublished("create-and-publish".equals(request.getParameter("action")));
    }

    @Override
    public Event persist(Event object) {
        return repository.create(object);
    }

    public Event update(Event object)
    {
        return repository.update(object);
    }

    public Optional<Event> find(int id)
    {
        return repository.find(id);
    }

    public EventRepository getRepository()
    {
        return this.repository;
    }

    public void addUserToEvent(User currentUser, int id) {
        Optional<Event> eventOptionnal = repository.find(id);

        if (!eventOptionnal.isPresent())
        {
            return;
        }

        Event event = eventOptionnal.get();

        if (!event.isSubscribable(currentUser))
        {
            return;
        }

        event.addSubscribers(currentUser);
    }
}
