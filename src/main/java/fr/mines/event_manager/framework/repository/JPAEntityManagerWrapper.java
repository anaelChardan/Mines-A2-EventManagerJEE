package fr.mines.event_manager.framework.repository;

import fr.mines.event_manager.event.entity.Address;
import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.user.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JPAEntityManagerWrapper {
    private static JPAEntityManagerWrapper instance = null;

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("EventManagerDB");
    private EntityManager entityManager;
    private boolean closed = false;

    private JPAEntityManagerWrapper() {
        this.entityManager = factory.createEntityManager();
    }

    public static synchronized JPAEntityManagerWrapper getInstance() {
        if (null == instance) {
            instance = new JPAEntityManagerWrapper();
            instance.populate();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void close() {
        if (!closed) {
            entityManager.close();
            factory.close();
            closed = true;
        }
    }

    protected void populate() {
        SimpleDateFormat sdf = new SimpleDateFormat();
        User anael = (new User())
                .setEmail("anael.chardan@gmail.com")
                .setFirstName("anael")
                .setLastName("chardan")
                .setPassword("anaelChardan")
                .setCompany("Akeneo")
                ;

        User damien = (new User())
                .setEmail("damien.renaud@gmail.com")
                .setFirstName("damien")
                .setLastName("renaud")
                .setPassword("damienRenaud")
                .setCompany("Sopra Steria")
                ;

        User flora = (new User())
                .setEmail("flora.pelletier@gmail.com")
                .setFirstName("flora")
                .setLastName("pelletier")
                .setPassword("flora")
                .setCompany("Sopra Steria")
                ;

        List<Event> events = new ArrayList<>();

        Address address = (new Address())
                .setAddress1("Rue du bonheur")
                .setCity("Nantes")
                .setZipCode("44000")
                .setCountry("France")
                ;

        Event event1 = (new Event())
                .setAddress(address)
                .setAuthor(anael)
                .setName(" 1 Event futur non publié (anael)")
                .setDescription("Evenement futur non publié par Anael")
                .setStartDate(Date.from(LocalDate.now().plusDays(2).atTime(18,0).toInstant(ZoneOffset.UTC)))
                .setEndDate(Date.from(LocalDate.now().plusDays(2).atTime(20,0).toInstant(ZoneOffset.UTC)))
                .setPublished(false)
                .setMaxTickets(3000)
                .setPrice(0.0);

        Event event1A = (new Event())
                .setAddress(address)
                .setAuthor(anael)
                .setName(" 2 Event futur non publié 2 (anael)")
                .setDescription("Evenement futur non publié par Anael")
                .setStartDate(Date.from(LocalDate.now().plusDays(3).atTime(18,0).toInstant(ZoneOffset.UTC)))
                .setEndDate(Date.from(LocalDate.now().plusDays(3).atTime(20,0).toInstant(ZoneOffset.UTC)))
                .setPublished(false)
                .setMaxTickets(100)
                .setPrice(0.0);

        Event event1B = (new Event())
                .setAddress(address)
                .setAuthor(damien)
                .setName(" 3 Event futur non publié 2 (damien)")
                .setDescription("Evenement futur non publié par Damien")
                .setStartDate(Date.from(LocalDate.now().plusDays(4).atTime(18,0).toInstant(ZoneOffset.UTC)))
                .setEndDate(Date.from(LocalDate.now().plusDays(4).atTime(20,0).toInstant(ZoneOffset.UTC)))
                .setPublished(false)
                .setMaxTickets(100)
                .setPrice(0.0);

        Event event2 = (new Event())
                .setAddress(address)
                .setAuthor(anael)
                .setName(" 4 Event futur publié (anael)")
                .setDescription("Evenement futur publié par Anael (avec Flora et Damien)")
                .setStartDate(Date.from(LocalDate.now().plusDays(2).atTime(18,0).toInstant(ZoneOffset.UTC)))
                .setEndDate(Date.from(LocalDate.now().plusDays(2).atTime(20,0).toInstant(ZoneOffset.UTC)))
                .setPublished(true)
                .setMaxTickets(3000)
                .setPrice(0.0)
                .addSubscribers(flora)
                .addSubscribers(damien);

        Event event2A = (new Event())
                .setAddress(address)
                .setAuthor(anael)
                .setName(" 5 Event futur publié (anael)")
                .setDescription("Evenement futur publié par Anael (avec Flora)")
                .setStartDate(Date.from(LocalDate.now().plusDays(3).atTime(18,0).toInstant(ZoneOffset.UTC)))
                .setEndDate(Date.from(LocalDate.now().plusDays(3).atTime(20,0).toInstant(ZoneOffset.UTC)))
                .setPublished(true)
                .setMaxTickets(3000)
                .setPrice(0.0)
                .addSubscribers(flora);

        Event event2B = (new Event())
                .setAddress(address)
                .setAuthor(damien)
                .setName(" 6 Event futur publié (damien)")
                .setDescription("Evenement futur publié par Damien (avec Flora et Anael)")
                .setStartDate(Date.from(LocalDate.now().plusDays(3).atTime(18,0).toInstant(ZoneOffset.UTC)))
                .setEndDate(Date.from(LocalDate.now().plusDays(3).atTime(20,0).toInstant(ZoneOffset.UTC)))
                .setPublished(true)
                .setMaxTickets(3000)
                .setPrice(0.0)
                .addSubscribers(flora)
                .addSubscribers(anael);

        Event event3 = (new Event())
                .setAddress(address)
                .setAuthor(anael)
                .setName(" 7 Event en cours publié (anael)")
                .setDescription("Evenement publié par Anael (avec Flora et Damien)")
                .setStartDate(Date.from(LocalDate.now().minusDays(1).atTime(18,0).toInstant(ZoneOffset.UTC)))
                .setEndDate(Date.from(LocalDate.now().plusDays(1).atTime(18,0).toInstant(ZoneOffset.UTC)))
                .setPublished(true)
                .setMaxTickets(100)
                .setPrice(0.0)
                .addSubscribers(flora)
                .addSubscribers(damien);

        Event event4 = (new Event())
                .setAddress(address)
                .setAuthor(damien)
                .setName(" 8 Event futur publié (damien)")
                .setDescription("Anael doit s'inscrire")
                .setStartDate(Date.from(LocalDate.now().plusDays(10).atTime(18,0).toInstant(ZoneOffset.UTC)))
                .setEndDate(Date.from(LocalDate.now().plusDays(10).atTime(20,0).toInstant(ZoneOffset.UTC)))
                .setPublished(true)
                .setMaxTickets(100)
                .setPrice(0.0)
                .addSubscribers(flora);

        Event event5 = (new Event())
                .setAddress(address)
                .setAuthor(anael)
                .setName(" 9 Event passé et publié (anael)")
                .setDescription("Evenement passé et publié par Anael (avec Damien et Flora)")
                .setStartDate(Date.from(LocalDate.now().minusDays(2).atTime(18,0).toInstant(ZoneOffset.UTC)))
                .setEndDate(Date.from(LocalDate.now().minusDays(2).atTime(20,0).toInstant(ZoneOffset.UTC)))
                .setPublished(true)
                .setMaxTickets(100)
                .setPrice(0.0)
                .addSubscribers(flora)
                .addSubscribers(damien);

        Event event5A = (new Event())
                .setAddress(address)
                .setAuthor(anael)
                .setName(" 10 Event passé et publié (anael)")
                .setDescription("Evenement passé et publié par Anael (avec Flora)")
                .setStartDate(Date.from(LocalDate.now().minusDays(4).atTime(18,0).toInstant(ZoneOffset.UTC)))
                .setEndDate(Date.from(LocalDate.now().minusDays(4).atTime(20,0).toInstant(ZoneOffset.UTC)))
                .setPublished(true)
                .setMaxTickets(100)
                .setPrice(0.0)
                .addSubscribers(flora);

        Event event5B = (new Event())
                .setAddress(address)
                .setAuthor(damien)
                .setName(" 11 Event passé et publié (damien)")
                .setDescription("Evenement passé et publié par Damien (avec Anael et Flora)")
                .setStartDate(Date.from(LocalDate.now().minusDays(4).atTime(18,0).toInstant(ZoneOffset.UTC)))
                .setEndDate(Date.from(LocalDate.now().minusDays(4).atTime(20,0).toInstant(ZoneOffset.UTC)))
                .setPublished(true)
                .setMaxTickets(100)
                .setPrice(0.0)
                .addSubscribers(flora)
                .addSubscribers(anael);

        Event event5C = (new Event())
                .setAddress(address)
                .setAuthor(damien)
                .setName(" 12 Event passé et publié (damien)")
                .setDescription("Evenement passé et publié par Damien (avec Flora)")
                .setStartDate(Date.from(LocalDate.now().minusDays(4).atTime(18,0).toInstant(ZoneOffset.UTC)))
                .setEndDate(Date.from(LocalDate.now().minusDays(4).atTime(20,0).toInstant(ZoneOffset.UTC)))
                .setPublished(true)
                .setMaxTickets(100)
                .setPrice(0.0)
                .addSubscribers(flora);

        events.add(event1);
        events.add(event2);
        events.add(event3);
        events.add(event4);
        events.add(event5);
        events.add(event1A);
        events.add(event1B);
        events.add(event2A);
        events.add(event2B);
        events.add(event5A);
        events.add(event5B);
        events.add(event5C);
        this.entityManager.getTransaction().begin();
        events.forEach(e -> this.entityManager.persist(e));
        this.entityManager.getTransaction().commit();
    }
}
