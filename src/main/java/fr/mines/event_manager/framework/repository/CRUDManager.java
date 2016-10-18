package fr.mines.event_manager.framework.repository;

import fr.mines.event_manager.framework.entity.AbstractEntity;

import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class CRUDManager<T extends AbstractEntity> {
    enum Action { CREATE, UPDATE, DELETE }

    EntityManager entityManager = BaseEntityManagerWrapper.getInstance().getEntityManager();

    private Class classType;
    private String tableName;

    public CRUDManager() {
        try {
            this.classType = this.getClassType();
            Field tableNameField = classType.getDeclaredField("tableName");
            tableNameField.setAccessible(true);
            this.tableName = (String) tableNameField.get(null);
        } catch (IllegalAccessException | NoSuchFieldException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Class<T> getClassType() throws ClassNotFoundException {
        return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    protected String getTableName()
    {
        try {
            Field tableNameField = this.classType.getDeclaredField("tableName");
            tableNameField.setAccessible(true);
            return this.tableName = (String) tableNameField.get(null);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return "";
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
        return (List<T>) entityManager.createQuery("SELECT t FROM " + tableName + " t").getResultList();
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
}