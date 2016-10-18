package fr.mines.event_manager.framework;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface IPath {
    /**
     * Extract parameters in a path string from a corresponding path pattern.
     *
     * For instance, Path.EVENT.extractParametersOf("/events/2")
     * return a Map of {eventId: 2}
     */
    public default Map<String, String> extractParametersOf(String pathStr) {
        Matcher patternMatcher = this.getEndPoint().matcher(pathStr);

        patternMatcher.matches(); // MANDATORY (TOREVIEW)

        Map<String, String> parameters = new HashMap<>();
        getNamedGroupCandidates(this.getEndPoint().pattern())
                .forEach(groupName -> parameters.put(groupName, patternMatcher.group(groupName)));

        return parameters;
    }

     default Set<String> getNamedGroupCandidates(String regex) {
        Set<String> namedGroups = new TreeSet<>();
        Matcher matcher = Pattern.compile("\\(\\?<([a-zA-Z][a-zA-Z0-9]*)>").matcher(regex);

        while (matcher.find()) {
            namedGroups.add(matcher.group(1));
        }

        return namedGroups;
    }

    Pattern getEndPoint();

    String getBase();

    String getFullPath();
}
