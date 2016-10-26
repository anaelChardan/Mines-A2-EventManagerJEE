package fr.mines.event_manager.event.manager;

import fr.mines.event_manager.app.repository.TankRepository;
import fr.mines.event_manager.event.entity.Address;
import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.framework.manager.BaseEntityManager;
import fr.mines.event_manager.framework.repository.BaseEntityManagerWrapper;
import fr.mines.event_manager.framework.security.UserProvider;

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

    private EventManager() {
    }

    public static EventManager getInstance() {
        if (null == instance) {
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
            startDate = request.getParameter("start_date").isEmpty() ? null : sdf.parse(request.getParameter("start_date"));
            endDate = request.getParameter("end_date").isEmpty() ? null : sdf.parse(request.getParameter("end_date"));
        } catch (ParseException e) {
        }

        Integer maxTickets = request.getParameter("max_tickets").isEmpty() ? null : Integer.parseInt(request.getParameter("max_tickets"));
        Double price = request.getParameter("price").isEmpty() ? null : Double.parseDouble(request.getParameter("price"));

        return (new Event())
                .setAddress(AddressManager.getInstance().create(request))
                .setAuthor(UserProvider.getCurrentUser(request))
                .setDescription(request.getParameter("description"))
                .setMaxTickets(maxTickets)
                .setName(request.getParameter("titre"))
                .setPrice(price)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setPublished("create-and-publish".equals(request.getParameter("action")));
    }

    @Override
    public Event persist(Event object) {
        return TankRepository.getInstance().getEventRepository().create(object);
    }

    public Optional<Event> find(int id) {
        return TankRepository.getInstance().getEventRepository().find(id);
    }

}
