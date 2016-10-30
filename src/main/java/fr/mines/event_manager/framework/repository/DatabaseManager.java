package fr.mines.event_manager.framework.repository;

import fr.mines.event_manager.event.entity.Address;
import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.framework.repository.utils.Field;
import fr.mines.event_manager.framework.entity.AbstractSelfManagedEntity;
import fr.mines.event_manager.user.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Consumer;

public abstract class DatabaseManager<T extends AbstractSelfManagedEntity> {
    protected enum Action {CREATE, READ, UPDATE, DELETE}

    protected CriteriaBuilder cb = null;
    protected Class classType;
    protected EntityManagerFactory factory = Persistence.createEntityManagerFactory("EventManagerDB");
    protected EntityManager entityManager;

    public DatabaseManager() {
        try {
            this.classType     = this.getClassType();
            this.entityManager = factory.createEntityManager();
            this.cb            = entityManager.getCriteriaBuilder();
            this.populate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Class<T> getClassType() throws ClassNotFoundException {
        return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public void performRequest(Consumer<EntityManager> request) {
        begin();
        request.accept(this.entityManager);
        commit();
    }

    public T create(T object) {

        this.performRequest(entityManager -> {
            entityManager.persist(object);
            entityManager.flush();
        });

        return object;
    }

    protected boolean update(Optional<T> object) {
        if (object.isPresent()) {
            this.performRequest(em -> em.merge(object.get()));
            return true;
        }

        return false;
    }

    protected boolean remove(Optional<T> object) {
        if (object.isPresent()) {
            this.performRequest(em -> em.remove(object.get()));
            return true;
        }

        return false;
    }

    public void close() {
        this.entityManager.close();
        this.factory.close();
    }

    protected void begin() {
        this.entityManager.getTransaction().begin();
    }

    protected void commit() {
        this.entityManager.getTransaction().commit();
    }

    protected AbstractMap.SimpleEntry<Root<T>, CommonAbstractCriteria> getBaseQuery(Action action) {
        if (action.equals(Action.READ)) {
            CriteriaQuery<T> c = cb.createQuery(this.classType);
            Root<T> root = c.from(this.classType);

            return new AbstractMap.SimpleEntry<>(root, c);
        }

        if (action.equals(Action.UPDATE)) {
            CriteriaUpdate<T> c = cb.createCriteriaUpdate(this.classType);
            Root<T> root = c.from(this.classType);

            return new AbstractMap.SimpleEntry<>(root, c);
        }

        if (action.equals(Action.DELETE)) {
            CriteriaDelete<T> c = cb.createCriteriaDelete(this.classType);
            Root<T> root = c.from(this.classType);

            return new AbstractMap.SimpleEntry<>(root, c);
        }

        return new AbstractMap.SimpleEntry<>(null, null);
    }

    protected Query getQueryBy(Field... fields) {
        AbstractMap.SimpleEntry<Root<T>, CommonAbstractCriteria> entry = this.getBaseQuery(Action.READ);

        List<Predicate> predicates = new ArrayList<>();
        for (Field field : fields) {
            switch (field.getFilter()) {
                case EQUAL:
                    predicates.add(cb.equal(entry.getKey().get(field.getLabel()), field.getValue()));
                    break;
                case LIKE:
                    predicates.add(cb.like(entry.getKey().get(field.getLabel()), (String) field.getValue()));
                    break;
            }
        }

        (((CriteriaQuery<T>) entry.getValue()))
                .where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

        return this.entityManager.createQuery((((CriteriaQuery<T>) entry.getValue())));
    }

    protected void populate() {
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
                .addSubscribers(anael)
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
                .addSubscribers(flora)
                .addSubscribers(anael)
        );

        this.entityManager.getTransaction().begin();
        events.forEach(e -> this.entityManager.persist(e));
        this.entityManager.getTransaction().commit();
    }
}
