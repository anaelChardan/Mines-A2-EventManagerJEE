package fr.mines.event_manager.event.repository;

import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.framework.repository.CRUDManager;
import fr.mines.event_manager.user.entity.User;

import javax.persistence.criteria.*;
import java.util.*;

public class EventRepository extends CRUDManager<Event> {

    public List<Event> getNextOrInProgressParticipations(User user, boolean isMember) {
        AbstractMap.SimpleEntry<Root<Event>, CommonAbstractCriteria> entry = this.getBaseQuery(Action.READ);

        Root<Event> root = entry.getKey();
        CriteriaQuery<Event> criteriaQuery = (CriteriaQuery<Event>) entry.getValue();
        Predicate inProgress = cb.greaterThan(root.get("endDate"), new Date());
        Predicate published = this.getPredicateEqual(root, "published", true, true);
        Predicate member = this.getPredicateIsMember(root, "subscribers", user, isMember);
        Predicate notAuthor = cb.notEqual(root.join("author").get("id"),user.getId());
        criteriaQuery.where(cb.and(inProgress, published, member, notAuthor));
        criteriaQuery.orderBy(cb.asc(root.get("startDate")));
        return getEntityManager().createQuery(criteriaQuery).getResultList();

    }

    public List<Event> getEventsPassed(User user) {
        AbstractMap.SimpleEntry<Root<Event>, CommonAbstractCriteria> entry = this.getBaseQuery(Action.READ);

        Root<Event> root = entry.getKey();
        CriteriaQuery<Event> criteriaQuery = (CriteriaQuery<Event>) entry.getValue();
        Predicate passed = cb.lessThan(root.get("endDate"),new Date());
        Predicate published = this.getPredicateEqual(root, "published", true, true);
        Predicate notAuthor = cb.notEqual(root.join("author").get("id"),user.getId());
        criteriaQuery.where(cb.and(passed,published,notAuthor));
        criteriaQuery.orderBy(cb.asc(root.get("startDate")));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    // PARTIE PROFILE UTILISATEUR

    public List<Event> getNextOrInProgressEvents(User user) {
        AbstractMap.SimpleEntry<Root<Event>, CommonAbstractCriteria> entry = this.getBaseQuery(Action.READ);

        Root<Event> root = entry.getKey();
        CriteriaQuery<Event> criteriaQuery = (CriteriaQuery<Event>) entry.getValue();
        Predicate inProgress = cb.greaterThan(root.get("endDate"), new Date());
        Predicate author = cb.equal(root.get("author"),user);
        criteriaQuery.where(cb.and(inProgress, author));
        criteriaQuery.orderBy(cb.asc(root.get("startDate")));
        return getEntityManager().createQuery(criteriaQuery).getResultList();

    }

    public List<Event> getPastEvents(User user) {
        AbstractMap.SimpleEntry<Root<Event>, CommonAbstractCriteria> entry = this.getBaseQuery(Action.READ);

        Root<Event> root = entry.getKey();
        CriteriaQuery<Event> criteriaQuery = (CriteriaQuery<Event>) entry.getValue();
        Predicate inProgress = cb.lessThan(root.get("endDate"), new Date());
        Predicate author = cb.equal(root.get("author"),user);
        criteriaQuery.where(cb.and(inProgress, author));
        criteriaQuery.orderBy(cb.asc(root.get("startDate")));
        return getEntityManager().createQuery(criteriaQuery).getResultList();

    }

    public List<Event> getPastParticipations(User user) {
        AbstractMap.SimpleEntry<Root<Event>, CommonAbstractCriteria> entry = this.getBaseQuery(Action.READ);

        Root<Event> root = entry.getKey();
        CriteriaQuery<Event> criteriaQuery = (CriteriaQuery<Event>) entry.getValue();
        Predicate past = cb.lessThan(root.get("endDate"), new Date());
        Predicate published = this.getPredicateEqual(root, "published", true, true);
        Predicate member = this.getPredicateIsMember(root, "subscribers", user, true);
//        Predicate notAuthor = cb.notEqual(root.get("author"),user);
        criteriaQuery.where(cb.and(past, published,member));
        criteriaQuery.orderBy(cb.asc(root.get("startDate")));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }
}
