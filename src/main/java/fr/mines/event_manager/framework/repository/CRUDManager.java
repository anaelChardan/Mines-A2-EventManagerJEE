package fr.mines.event_manager.framework.repository;

import fr.mines.event_manager.framework.entity.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.lang.reflect.ParameterizedType;
import java.util.AbstractMap;
import java.util.List;
import java.util.Optional;

public abstract class CRUDManager<T extends AbstractEntity> {
    enum Action { CREATE, READ, UPDATE, DELETE }

    CriteriaBuilder cb = null;

    private Class classType;

    public CRUDManager() {
        try {
            this.classType = this.getClassType();
            this.cb        = this.getEntityManager().getCriteriaBuilder();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected EntityManager getEntityManager()
    {
        return BaseEntityManagerWrapper.getInstance().getEntityManager();
    }

    protected Class<T> getClassType() throws ClassNotFoundException {
        return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public boolean updateDatabase(Action action, Optional<T> object, boolean withCommit)
    {
        if (!object.isPresent())
        {
            return false;
        }

        getEntityManager().getTransaction().begin();

        switch (action)
        {
            case CREATE:
                getEntityManager().merge(object.get());
                break;
            case UPDATE:
                getEntityManager().merge(object.get());
                break;
            case DELETE:
                getEntityManager().remove(object.get());
                break;
        }

        if (withCommit)
        {
            getEntityManager().getTransaction().commit();
        }

        return true;
    }

    public Optional<T> find(int id)
    {
        return Optional.ofNullable((T) getEntityManager().find(classType, id));
    }

    public List<T> findAll()
    {
        AbstractMap.SimpleEntry<Root<T>, CommonAbstractCriteria> entry = this.getBaseQuery(Action.READ);
        return getEntityManager().createQuery(((CriteriaQuery<T>)entry.getValue()).select(entry.getKey())).getResultList();
    }

    public T update(T object, boolean withCommit)
    {
        this.updateDatabase(Action.UPDATE, Optional.of(object), withCommit);

        return object;
    }

    public T create(T object, boolean withCommit)
    {
        this.updateDatabase(Action.CREATE, Optional.of(object), withCommit);

        return object;
    }

    public List<T> create(List<T> objects, boolean withCommit)
    {
        for (T object : objects)
        {
            this.create(object, false);
        }

        if (withCommit)
        {
            getEntityManager().getTransaction().commit();
        }

        return objects;
    }

    public boolean delete(int id, boolean withCommit)
    {
        return this.updateDatabase(Action.DELETE, find(id), withCommit);
    }

    public AbstractMap.SimpleEntry<Root<T>, CommonAbstractCriteria> getBaseQuery(Action action)
    {
        if (action.equals(Action.READ))
        {
            CriteriaQuery<T> c = cb.createQuery(this.classType);
            Root<T> root       = c.from(this.classType);

            return new AbstractMap.SimpleEntry<>(root, c);
        }

        if (action.equals(Action.UPDATE))
        {
            CriteriaUpdate<T> c = cb.createCriteriaUpdate(this.classType);
            Root<T> root        = c.from(this.classType);

            return new AbstractMap.SimpleEntry<>(root, c);
        }

        if (action.equals(Action.DELETE))
        {
            CriteriaDelete<T> c = cb.createCriteriaDelete(this.classType);
            Root<T> root        = c.from(this.classType);

            return new AbstractMap.SimpleEntry<>(root, c);
        }

        return new AbstractMap.SimpleEntry<>(null, null);
    }
}
