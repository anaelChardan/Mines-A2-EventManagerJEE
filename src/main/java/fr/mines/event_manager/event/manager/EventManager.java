package fr.mines.event_manager.event.manager;

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

/**
 * Created by Damien on 24/10/2016.
 */
public class EventManager implements BaseEntityManager<Event> {

    private static EventManager instance = null;

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

        String action = request.getParameter("action");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int maxTickets = Integer.parseInt(request.getParameter("max_tickets"));
        Double price = Double.parseDouble(request.getParameter("price"));

        // Gestion des dates
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = sdf.parse(request.getParameter("start_date"));
            endDate = sdf.parse(request.getParameter("end_date"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Gestion de l'adresse
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String zipCode = request.getParameter("zip_code");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        Address address = (new Address())
                .setAddress1(address1)
                .setAddress2(address2)
                .setZipCode(zipCode)
                .setCity(city)
                .setCountry(country);

        Event event = (new Event())
                .setAddress(address)
                .setAuthor(UserProvider.getCurrentUser(request))
                .setDescription(description)
                .setMaxTickets(maxTickets)
                .setName(name)
                .setPrice(price)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setPublished("create-and-publish".equals(action));

        return event;
    }


}
