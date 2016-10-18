package fr.mines.event_manager.framework;

import java.util.regex.Pattern;

public enum Path implements IPath {

    HOME (PathConstants.BLANK, Pattern.compile("/")),
    LOGIN (PathConstants.AUTH, Pattern.compile("/login")),
    LOGOUT (PathConstants.AUTH, Pattern.compile("/logout")),
    EVENTS (PathConstants.EVENTS, Pattern.compile("/")),
    EVENT (PathConstants.EVENTS, Pattern.compile("/(?<eventId>\\d+)")),
    PROFIL (PathConstants.PROFIL, Pattern.compile("/"));

    private final String base;
    private final Pattern endPoint;

    Path(final String base, final Pattern endPoint) {
        this.base = base;
        this.endPoint = endPoint;
    }

    @Override
    public Pattern getEndPoint() {
        return endPoint;
    }

    @Override
    public String getBase() {
        return base;
    }

    @Override
    public String getFullPath() {
        return base + endPoint.pattern();
    }

    public static class PathConstants {
        public static final String BLANK = "";
        public static final String EVENTS = "/events";
        public static final String AUTH = "/auth";
        public static final String PROFIL = "/profil";
    }
}
