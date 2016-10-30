package fr.mines.event_manager.home_made_framework.repository.utils;

public class Field<T> {
    public enum Filter { EQUAL, LIKE }

    protected String label;

    protected T value;

    protected Filter filter;

    public Field(String label, T value, Filter filter)
    {
        this.label = label;
        this.value = value;
        this.filter = filter;
    }

    public String getLabel() {
        return label;
    }

    public T getValue() {
        return value;
    }

    public Filter getFilter() {
        return filter;
    }
}
