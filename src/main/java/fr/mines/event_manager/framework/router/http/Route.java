package fr.mines.event_manager.framework.router.http;

import fr.mines.event_manager.framework.router.utils.WrappedServletAction;

import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Route {
    public enum PROTECTION_LEVEL {NONE, CONNECTED, PROPRIETARY}

    protected Pattern pattern;

    protected PROTECTION_LEVEL protectionLevel;

    protected Consumer<WrappedServletAction> consumedMethod;

    public Route(Pattern pattern, PROTECTION_LEVEL protectionLevel, Consumer<WrappedServletAction> consumedMethod) {
        this.pattern = pattern;
        this.protectionLevel = protectionLevel;
        this.consumedMethod = consumedMethod;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public PROTECTION_LEVEL getProtectionLevel() {
        return protectionLevel;
    }

    public Consumer<WrappedServletAction> getConsumer()
    {
        return this.consumedMethod;
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
