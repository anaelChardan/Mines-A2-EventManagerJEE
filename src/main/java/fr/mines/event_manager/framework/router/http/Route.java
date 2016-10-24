package fr.mines.event_manager.framework.router.http;

import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Route {
    public enum PROTECTION_LEVEL {NONE, CONNECTED, PROPRIETARY}

    protected Pattern pattern;

    protected String method;

    protected PROTECTION_LEVEL protectionLevel;

    public Route(String method, Pattern pattern, PROTECTION_LEVEL protectionLevel) {
        this.method = method;
        this.pattern = pattern;
        this.protectionLevel = protectionLevel;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public String getMethod() {
        return method;
    }

    public PROTECTION_LEVEL getProtectionLevel() {
        return protectionLevel;
    }

    public Set<String> getNamedGroups() {
        Set<String> namedGroups = new TreeSet<>();
        Matcher m = Pattern.compile("\\(\\?<([a-zA-Z][a-zA-Z0-9]*)>").matcher(this.pattern.pattern());

        while (m.find()) {
            namedGroups.add(m.group(1));
        }

        return namedGroups;
    }
}
