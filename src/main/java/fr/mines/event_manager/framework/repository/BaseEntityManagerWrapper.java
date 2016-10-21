package fr.mines.event_manager.framework.repository;

import fr.mines.event_manager.app.servlet.repository.TankRepository;
import fr.mines.event_manager.event.entity.Address;
import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.user.entity.User;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseEntityManagerWrapper {
    private static BaseEntityManagerWrapper instance = null;

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("EventManagerDB");
    private EntityManager entityManager;

    private BaseEntityManagerWrapper()
    {
        this.entityManager = factory.createEntityManager();
    }

    public static BaseEntityManagerWrapper getInstance()
    {
        if (null == instance)
        {
            instance = new BaseEntityManagerWrapper();
            instance.populate();
        }

        return instance;
    }

    public EntityManager getEntityManager()
    {
        return entityManager;
    }

    public void close()
    {
        entityManager.close();
        factory.close();
    }

    protected void populate() {

        System.out.println("YOYO1");

        User anael = (new User())
                .setEmail("anael.chardan@gmail.com")
                .setFirstName("anael")
                .setLastName("chardan")
                .setPassword("anaelChardan")
                ;

        User damien = (new User())
                .setEmail("damien.renaud@gmail.com")
                .setFirstName("damien")
                .setLastName("renaud")
                .setPassword("damienRenaud")
                ;

        User flora = (new User())
                .setEmail("flora.pelletier@gmail.com")
                .setFirstName("flora")
                .setLastName("pelletier")
                .setPassword("floraPelletier")
                ;

        List<Event> events = new ArrayList<>();

        Address address = (new Address())
                .setAddress1("Rue du bonheur")
                .setCity("Nantes")
                .setZipCode("44000")
                .setCountry("France")
                ;

        events.add(
            (new Event())
                .setAddress(address)
                .setName("DEV FEST")
                .setAuthor(anael)
                .setDescription("A super fun dev party")
                .setPublished(false)
                .setStartDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .setEndDate(Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .setMaxTickets(100)
        );

       events.add((new Event())
            .setAddress(address)
            .setName("Music Session")
            .setAuthor(flora)
            .setDescription("A super music party")
            .setPublished(false)
            .setStartDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))
            .setEndDate(Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()))
            .setMaxTickets(100)
       );

        events.add((new Event())
                .setAddress(address)
                .setName("Badminton Session")
                .setAuthor(damien)
                .setDescription("A super Badminton session")
                .setPublished(false)
                .setStartDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .setEndDate(Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .setMaxTickets(100)
        );

        this.entityManager.getTransaction().begin();
        events.forEach(e -> this.entityManager.persist(e));
        this.entityManager.getTransaction().commit();

        List<Event> eventList = TankRepository.getInstance().getEventRepository().findAll();
        System.out.println("YOYO");
    }
}
