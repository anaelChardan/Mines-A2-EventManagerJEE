package fr.mines.event_manager.framework.router.http;

import fr.mines.event_manager.framework.router.utils.WrappedServletAction;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ComputedRoute {
    protected Map<String, String> parameters = new HashMap<>();

    protected Route.PROTECTION_LEVEL protectionLevel;

    protected Consumer<WrappedServletAction> consumedMethod;

    public ComputedRoute(Route.PROTECTION_LEVEL protectionLevel, Consumer<WrappedServletAction> consumedMethod) {
        this.protectionLevel = protectionLevel;
        this.consumedMethod = consumedMethod;
    }

    public void add(String key, String value)
    {
        this.parameters.put(key, value);
    }

    public void add(Map<String, String> values)
    {
        this.parameters.putAll(values);
    }

    public Map<String, String> getParameters()
    {
        return parameters;
    }

    public Route.PROTECTION_LEVEL getProtectionLevel() {
        return protectionLevel;
    }

    public boolean isProtected()
    {
        return protectionLevel != Route.PROTECTION_LEVEL.NONE;
    }

    public void consume(WrappedServletAction action)
    {
        this.consumedMethod.accept(action);
    }
}