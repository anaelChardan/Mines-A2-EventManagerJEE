package fr.mines.event_manager.framework.repository;

import fr.mines.event_manager.framework.repository.utils.Field;
import fr.mines.event_manager.framework.entity.AbstractSelfManagedEntity;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.AbstractMap;
import java.util.List;
import java.util.Optional;

public abstract class CRUDManager<T extends AbstractSelfManagedEntity> extends DatabaseManager<T> {

    /*****************
     * CREATE
     *****************/


    public List<T> create(List<T> objects) {
        for (T object : objects) {
            this.create(object);
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
        return this.getEntityManager().createQuery(((CriteriaQuery<T>) entry.getValue()).select(entry.getKey())).getResultList();
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

    public T update(T object) {
        this.update(Optional.of(object));

        return object;
    }

    /*****************
     * DELETE
     *****************/

    public boolean delete(int id) {
        return this.remove(find(id));
    }

    public boolean delete(T object)
    {
        return this.remove(Optional.of(object));
    }
}
