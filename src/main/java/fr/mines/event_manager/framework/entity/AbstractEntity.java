package fr.mines.event_manager.framework.entity;

/**
 * Created by Damien on 25/10/2016.
 */
public abstract class AbstractEntity {
    public abstract Integer getId();

    public static String getTableName()
    {
        return "";
    }
}
