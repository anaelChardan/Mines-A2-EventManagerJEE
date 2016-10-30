package fr.mines.event_manager.framework.manager;

import fr.mines.event_manager.framework.entity.AbstractEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Damien on 24/10/2016.
 */
public interface BaseEntityManager<T extends AbstractEntity>{
    T create(HttpServletRequest request);
    T persist(T object);
}
