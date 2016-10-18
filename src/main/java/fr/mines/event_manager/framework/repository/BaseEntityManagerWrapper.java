package fr.mines.event_manager.framework.repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;

public class BaseEntityManagerWrapper {
    private static BaseEntityManagerWrapper instance = null;

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("EventManagerDB");
    private EntityManager entityManager;

    private BaseEntityManagerWrapper()
    {
        this.entityManager = factory.createEntityManager();
    }

    public static BaseEntityManagerWrapper getInstance()
    {
        if (null == instance)
        {
            instance = new BaseEntityManagerWrapper();
        }

        return instance;
    }

    public EntityManager getEntityManager()
    {
        return entityManager;
    }

    public void close()
    {
        entityManager.close();
        factory.close();
    }
}
