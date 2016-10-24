package fr.mines.event_manager.framework.router.http;

import java.util.HashMap;
import java.util.Map;

public class ComputedRoute {
    protected String method;

    protected Map<String, String> parameters = new HashMap<>();

    protected Route.PROTECTION_LEVEL protectionLevel;

    public ComputedRoute(String method, Route.PROTECTION_LEVEL protectionLevel) {
        this.method = method;
        this.protectionLevel = protectionLevel;
    }

    public void add(String key, String value)
    {
        this.parameters.put(key, value);
    }

    public String getMethodName()
    {
        return method;
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
}