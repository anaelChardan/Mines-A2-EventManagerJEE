package fr.mines.event_manager.framework.entity;

public abstract class AbstractEntity {
    public abstract Integer getId();

    public static String getTableName()
    {
        return "";
    }
}
