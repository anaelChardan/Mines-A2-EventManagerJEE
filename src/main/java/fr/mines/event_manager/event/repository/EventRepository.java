package fr.mines.event_manager.event.repository;

import fr.mines.event_manager.app.repository.TankRepository;
import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.framework.repository.CRUDManager;
import fr.mines.event_manager.framework.repository.utils.Field;
import fr.mines.event_manager.user.entity.User;

import javax.persistence.criteria.CommonAbstractCriteria;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class EventRepository extends CRUDManager<Event> {
    public List<Event> getSubscribedEventsByUserSortedByDate(User user)
    {
        AbstractMap.SimpleEntry<Root<Event>, CommonAbstractCriteria> entry = this.getBaseQuery(Action.READ);

        Root<Event> root = entry.getKey();
        CriteriaQuery<Event> criteriaQuery = (CriteriaQuery<Event>) entry.getValue();
        criteriaQuery.where(this.getPredicateIsMember(root, "subscribers", user));
        criteriaQuery.orderBy(cb.asc(root.get("startDate")));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    public List<Event> getEventsCreatedByUserSortedByDate(User user)
    {
        AbstractMap.SimpleEntry<Root<Event>, CommonAbstractCriteria> entry = this.getBaseQuery(Action.READ);

        Root<Event> root = entry.getKey();
        CriteriaQuery<Event> criteriaQuery = (CriteriaQuery<Event>) entry.getValue();
        criteriaQuery.where(getPredicateEqual(root, "author", user));
        criteriaQuery.orderBy(cb.asc(root.get("startDate")));

        return getEntityManager().createQuery(criteriaQuery).getResultList();
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

        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    public void addUserToEvent(Integer userId, Integer eventId)
    {
        Optional<Event> event = this.findSingleBy(new Field<Integer>("id", eventId, Field.Filter.EQUAL));
        Optional<User>  user  = TankRepository.getInstance().getUserRepository().findSingleBy(new Field<Integer>("id", userId, Field.Filter.EQUAL));

        if (event.isPresent() && user.isPresent())
        {
            event.get().addSubscribers(user.get());
            update(event);
        }
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
