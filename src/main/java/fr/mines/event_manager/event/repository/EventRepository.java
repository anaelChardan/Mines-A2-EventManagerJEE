package fr.mines.event_manager.event.repository;

import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.framework.repository.CRUDManager;
import fr.mines.event_manager.user.entity.User;

import javax.persistence.Query;
import javax.persistence.criteria.CommonAbstractCriteria;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EventRepository extends CRUDManager<Event> {
    public List<Event> getSubscribedEventsByUserSortedByDate(User user)
    {
        AbstractMap.SimpleEntry<Root<Event>, CommonAbstractCriteria> entry = this.getBaseQuery(Action.READ);

        Root<Event> root = entry.getKey();
        CriteriaQuery<Event> criteriaQuery = (CriteriaQuery<Event>) entry.getValue();
        criteriaQuery.where(this.getPredicateIsMember(root, "subscribers", user));
        criteriaQuery.orderBy(cb.asc(root.get("startDate")));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<Event> getSubscribedEventsByUserSortedByDateBeforeNow(User user)
    {
        AbstractMap.SimpleEntry<Root<Event>, CommonAbstractCriteria> entry = this.getBaseQuery(Action.READ);

        Root<Event> root = entry.getKey();
        Predicate subscribers = this.getPredicateIsMember(root, "subscribers", user);
        Predicate date = cb.lessThan(root.get("endDate"), new Date());

        CriteriaQuery<Event> criteriaQuery = (CriteriaQuery<Event>) entry.getValue();
        criteriaQuery.where(subscribers,date);
        criteriaQuery.orderBy(cb.asc(root.get("startDate")));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<Event> getSubscribedEventsByUserSortedByDateAfterNow(User user)
    {
        AbstractMap.SimpleEntry<Root<Event>, CommonAbstractCriteria> entry = this.getBaseQuery(Action.READ);

        Root<Event> root = entry.getKey();
        Predicate subscribers = this.getPredicateIsMember(root, "subscribers", user);
        Predicate date = cb.greaterThan(root.get("endDate"), new Date());

        CriteriaQuery<Event> criteriaQuery = (CriteriaQuery<Event>) entry.getValue();
        criteriaQuery.where(subscribers,date);
        criteriaQuery.orderBy(cb.asc(root.get("startDate")));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<Event> getEventsCreatedByUserSortedByDate(User user)
    {
        AbstractMap.SimpleEntry<Root<Event>, CommonAbstractCriteria> entry = this.getBaseQuery(Action.READ);

        Root<Event> root = entry.getKey();
        CriteriaQuery<Event> criteriaQuery = (CriteriaQuery<Event>) entry.getValue();
        criteriaQuery.where(getPredicateEqual(root, "author", user));
        criteriaQuery.orderBy(cb.asc(root.get("startDate")));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<Event> getEventsSubscribableSortedByDate(User user)
    {
        AbstractMap.SimpleEntry<Root<Event>, CommonAbstractCriteria> entry = this.getBaseQuery(Action.READ);

        Root<Event> root = entry.getKey();
        CriteriaQuery<Event> criteriaQuery = (CriteriaQuery<Event>) entry.getValue();
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(getPredicateEqual(root, "author", user));
        predicates.add(getPredicateIsMember(root, "subscribers", user));
        criteriaQuery.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        criteriaQuery.orderBy(cb.asc(root.get("startDate")));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<Event> getEventsNotPassed()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date dayDate = new Date();
        String now = sdf.format(dayDate);
        try {
            dayDate = sdf.parse(now);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String queryStr = "SELECT e FROM Event e WHERE e.startDate > :dayDate order by e.startDate";
        Query query = entityManager.createQuery(queryStr);
        query.setParameter("dayDate",dayDate);
        List<Event> eventsPassed = query.getResultList();
        return eventsPassed;
    }

    public List<Event> getEventsPassed()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date dayDate = new Date();
        String now = sdf.format(dayDate);
        try {
            dayDate = sdf.parse(now);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String queryStr = "SELECT e FROM Event e WHERE e.endDate <= :dayDate order by e.startDate";
        Query query = entityManager.createQuery(queryStr);
        query.setParameter("dayDate",dayDate);
        List<Event> eventsNotPassed = query.getResultList();
        return eventsNotPassed;
    }

    protected Predicate getPredicateIsMember(Root<Event> root, String fieldName, Object concerned)
    {
        return cb.isMember(concerned, root.get(fieldName));
    }

    protected Predicate getPredicateEqual(Root<Event> root, String fieldName, Object concerned)
    {
        return cb.equal(root.get(fieldName), concerned);
    }
}
