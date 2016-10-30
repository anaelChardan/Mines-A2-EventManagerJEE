package fr.mines.event_manager.home_made_framework.entity;

public abstract class AbstractSelfManagedEntity extends AbstractEntity{
    public abstract Integer getId();

    public static String getTableName()
    {
        return "";
    }
}
