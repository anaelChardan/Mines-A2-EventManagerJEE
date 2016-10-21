package fr.mines.event_manager.framework.fixtures;

import fr.mines.event_manager.app.servlet.repository.TankRepository;
import fr.mines.event_manager.event.entity.Address;
import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.user.entity.User;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Populator {
    private static Populator instance = null;

    private boolean isPopulated = false;

    private Populator() { }

    public static Populator getInstance()
    {
        if (null == instance)
        {
            instance = new Populator();
        }

        return instance;
    }

    public void populate() {
        if (isPopulated)
        {
            return;
        }

        List<User> users = new ArrayList<>();

        User anael = (new User())
                .setEmail("anael.chardan@gmail.com")
                .setFirstName("anael")
                .setLastName("chardan")
                .setUsername("anaelChardan")
                ;

        users.add(anael);

        User damien = (new User())
                .setEmail("damien.renaud@gmail.com")
                .setFirstName("damien")
                .setLastName("renaud")
                .setUsername("damienRenaud")
                ;

        users.add(damien);

        User flora = (new User())
                .setEmail("flora.pelletier@gmail.com")
                .setFirstName("flora")
                .setLastName("pelletier")
                .setUsername("floraPelletier")
        ;

        users.add(flora);

        List<Event> events = new ArrayList<>();

        Address address = (new Address())
                .setAddress1("Rue du bonheur")
                .setCity("Nantes")
                .setZipCode("44000")
                .setCountry("France")
                ;

        Event event1 = (new Event())
                .setAddress(address)
                .setName("DEV FEST")
                .setAuthor(anael)
                .setDescription("A super fun dev party")
                .setPublished(false)
                .setStartDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .setEndDate(Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .setMaxTickets(100)
                ;

        events.add(event1);

        Event event2 = (new Event())
                .setAddress(address)
                .setName("Music Session")
                .setAuthor(flora)
                .setDescription("A super music party")
                .setPublished(false)
                .setStartDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .setEndDate(Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .setMaxTickets(100)
                ;

        events.add(event2);

        Event event3 = (new Event())
                .setAddress(address)
                .setName("Badminton Session")
                .setAuthor(flora)
                .setDescription("A ")
                .setPublished(false)
                .setStartDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .setEndDate(Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .setMaxTickets(100)
                ;

        events.add(event3);

        TankRepository.getInstance().getUserRepository().create(users, false);

        TankRepository.getInstance().getEventRepository().create(events, true);

        List<Event> eventList = TankRepository.getInstance().getEventRepository().findAll();
        System.out.println("YOYO");
    }
}
