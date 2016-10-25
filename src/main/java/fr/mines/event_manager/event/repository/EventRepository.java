package fr.mines.event_manager.event.repository;

import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.framework.repository.CRUDManager;
import fr.mines.event_manager.user.entity.User;

import javax.persistence.criteria.CommonAbstractCriteria;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.List;

public class EventRepository extends CRUDManager<Event> {
    public List<Event> getSubscribedEventsByUser(User user)
    {
        AbstractMap.SimpleEntry<Root<Event>, CommonAbstractCriteria> entry = this.getBaseQuery(Action.READ);

        Root<Event> root = entry.getKey();
        CriteriaQuery<Event> criteriaQuery = (CriteriaQuery<Event>) entry.getValue();
        criteriaQuery.where(this.cb.isMember(user, root.get("subscribers")));

        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }
}
