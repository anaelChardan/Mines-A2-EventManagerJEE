package fr.mines.event_manager.framework.repository;

public class Field {
    protected String label;

    protected Object value;

    public Field(String label, Object value)
    {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public Object getValue() {
        return value;
    }
}
