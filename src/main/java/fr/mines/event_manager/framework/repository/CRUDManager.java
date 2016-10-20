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

    EntityManager entityManager = BaseEntityManagerWrapper.getInstance().getEntityManager();

    CriteriaBuilder cb = entityManager.getCriteriaBuilder();

    private Class classType;

    public CRUDManager() {
        try {
            this.classType = this.getClassType();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Class<T> getClassType() throws ClassNotFoundException {
        return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public boolean updateDatabase(Action action, Optional<T> object)
    {
        if (!object.isPresent())
        {
            return false;
        }

        entityManager.getTransaction().begin();

        switch (action)
        {
            case CREATE:
                entityManager.merge(object.get());
                break;
            case UPDATE:
                entityManager.merge(object.get());
                break;
            case DELETE:
                entityManager.remove(object.get());
                break;
        }

        entityManager.getTransaction().commit();

        return true;
    }

    public Optional<T> find(int id)
    {
        return Optional.ofNullable((T) entityManager.find(classType, id));
    }

    public List<T> findAll()
    {
        AbstractMap.SimpleEntry<Root<T>, CommonAbstractCriteria> entry = this.getBaseQuery(Action.READ);
        return entityManager.createQuery(((CriteriaQuery<T>)entry.getValue()).select(entry.getKey())).getResultList();
    }

    public T update(T object)
    {
        this.updateDatabase(Action.UPDATE, Optional.of(object));

        return object;
    }

    public T create(T object)
    {
        this.updateDatabase(Action.CREATE, Optional.of(object));

        return object;
    }

    public boolean delete(int id)
    {
        return this.updateDatabase(Action.DELETE, find(id));
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
