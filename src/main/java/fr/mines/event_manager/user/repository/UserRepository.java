package fr.mines.event_manager.user.repository;

import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.framework.repository.CRUDManager;
import fr.mines.event_manager.user.entity.User;

import javax.persistence.criteria.CommonAbstractCriteria;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.AbstractMap;
import java.util.List;

public class UserRepository extends CRUDManager<User> {

    public List<User> getUsersByEnventSubscribed(Event event)
    {
        AbstractMap.SimpleEntry<Root<User>, CommonAbstractCriteria> entry = this.getBaseQuery(Action.READ);

        Root<User> root = entry.getKey();
        CriteriaQuery<User> criteriaQuery = (CriteriaQuery<User>) entry.getValue();
        criteriaQuery.where(this.getPredicateIsMember(root, "users", event));
        criteriaQuery.orderBy(cb.asc(root.get("name")));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    protected Predicate getPredicateIsMember(Root<User> root, String fieldName, Object concerned)
    {
        return cb.isMember(concerned, root.get(fieldName));
    }
}

