package fr.mines.event_manager.framework.repository;

import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.framework.entity.AbstractEntity;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.AbstractMap;
import java.util.List;
import java.util.Optional;

public abstract class CRUDManager<T extends AbstractEntity> extends DatabaseManager<T> {

    /*****************
     * CREATE
     *****************/

    public T create(T object, boolean withTransactionSelfManaged) {
        this.updateDatabase(Action.CREATE, Optional.of(object), withTransactionSelfManaged);

        return object;
    }

    public List<T> create(List<T> objects, boolean withTransactionSelfManaged) {
        if (withTransactionSelfManaged)
        {
            begin();
        }

        for (T object : objects) {
            this.create(object, false);
        }

        if (withTransactionSelfManaged) {
            commit();
        }

        return objects;
    }

    /*****************
     * READ
     *****************/

    public Optional<T> find(int id) {
        return this.findSingleBy(new Field<Integer>("id", id, Field.Filter.EQUAL));
    }

    public List<T> findAll() {
        AbstractMap.SimpleEntry<Root<T>, CommonAbstractCriteria> entry = this.getBaseQuery(Action.READ);
        return getEntityManager().createQuery(((CriteriaQuery<T>) entry.getValue()).select(entry.getKey())).getResultList();
    }

    public Optional<T> findSingleBy(Field... fields) {
        Query query = getQueryBy(fields).setMaxResults(1);

        List<T> list = query.getResultList();

        return Optional.ofNullable(list.isEmpty() ? null : (T)list.get(0));
    }

    public List<T> findBy(Field... fields) {
        return (List<T>) getQueryBy(fields).getResultList();
    }

    /*****************
     * UPDATE
     *****************/

    public T update(T object, boolean withTransactionSelfManaged) {
        this.updateDatabase(Action.UPDATE, Optional.of(object), withTransactionSelfManaged);

        return object;
    }

    /*****************
     * DELETE
     *****************/

    public boolean delete(int id, boolean withTransactionSelfManaged) {
        return this.updateDatabase(Action.DELETE, find(id), withTransactionSelfManaged);
    }
}
