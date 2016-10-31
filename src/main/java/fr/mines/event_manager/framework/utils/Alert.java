package fr.mines.event_manager.framework.utils;

import java.util.Collections;
import java.util.Map;

public class Alert {
    public enum TYPE {DANGER, SUCCESS};

    protected TYPE type;

    protected Map<String, String> messages;

    public Alert(TYPE type, Map<String, String> messages)
    {
        this.type = type;
        this.messages = messages;
    }

    public Alert(TYPE type, String message)
    {
        this.type = type;
        this.messages = Collections.singletonMap("", message);
    }

    public static Alert success(String message)
    {
        return new Alert(TYPE.SUCCESS, message);
    }

    public static Alert success(Map<String, String> messages)
    {
        return new Alert(TYPE.SUCCESS, messages);
    }

    public static Alert danger(String message)
    {
        return new Alert(TYPE.DANGER, message);
    }

    public static Alert danger(Map<String, String> messages)
    {
        return new Alert(TYPE.DANGER, messages);
    }

    public TYPE getType() {
        return type;
    }

    public Map<String, String> getMessages() {
        return messages;
    }
}
