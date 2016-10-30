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
                .setName("Event futur non publié")
                .setDescription("Les Utopiales de Nantes")
                .setStartDate(Date.from(LocalDate.now().plusDays(2).atTime(18,0).toInstant(ZoneOffset.UTC)))
                .setEndDate(Date.from(LocalDate.now().plusDays(2).atTime(20,0).toInstant(ZoneOffset.UTC)))
                .setPublished(false)
                .setMaxTickets(3000)
                .setPrice(0.0);

        Event event2 = (new Event())
                .setAddress(address)
                .setAuthor(anael)
                .setName("Event futur publié")
                .setDescription("Docker me if you can")
                .setStartDate(Date.from(LocalDate.now().plusDays(2).atTime(18,0).toInstant(ZoneOffset.UTC)))
                .setEndDate(Date.from(LocalDate.now().plusDays(2).atTime(20,0).toInstant(ZoneOffset.UTC)))
                .setPublished(true)
                .setMaxTickets(3000)
                .setPrice(0.0)
                .addSubscribers(flora)
                .addSubscribers(damien);

        Event event3 = (new Event())
                .setAddress(address)
                .setAuthor(anael)
                .setName("Event en cours publié")
                .setDescription("Docker me if you can")
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
                .setName("Event futur créé par damien et publié")
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
                .setName("Event passé et publié")
                .setDescription("Docker me if you can")
                .setStartDate(Date.from(LocalDate.now().minusDays(2).atTime(18,0).toInstant(ZoneOffset.UTC)))
                .setEndDate(Date.from(LocalDate.now().minusDays(2).atTime(20,0).toInstant(ZoneOffset.UTC)))
                .setPublished(true)
                .setMaxTickets(100)
                .setPrice(0.0)
                .addSubscribers(flora)
                .addSubscribers(damien);

        events.add(event1);
        events.add(event2);
        events.add(event3);
        events.add(event4);
        events.add(event5);
        this.entityManager.getTransaction().begin();
        events.forEach(e -> this.entityManager.persist(e));
        this.entityManager.getTransaction().commit();
    }
}
