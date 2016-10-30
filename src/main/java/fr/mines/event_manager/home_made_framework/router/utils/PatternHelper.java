package fr.mines.event_manager.home_made_framework.router.utils;

import java.util.regex.Pattern;

public class PatternHelper {
    public enum Position { BEGINNING, ENDING, ELSEWHERE }
    public enum Number { ONE, AT_LEAST_ONE, ZERO_OR_MORE, ONE_OR_NOT }

    public static Pattern createPattern(String... args)
    {
        StringBuilder builder = new StringBuilder();
        for(String arg: args)
        {
            builder.append(arg);
        }
        return Pattern.compile(builder.toString());
    }

    public static String integer(String name, Number number, Position position)
    {
        return buildString(name, "\\d", number, position);
    }

    public static String nonInteger(String name, Number number, Position position)
    {
        return buildString(name, "\\D", number, position);
    }

    public static String characterUppercase(String name, Number number, Position position)
    {
        return buildString(name, "[A-Z]", number, position);
    }

    public static String characterLowercase(String name, Number number, Position position)
    {
        return buildString(name, "[a-z]", number, position);
    }

    public static String characterUpperOrLower(String name, Number number, Position position)
    {
        return buildString(name, "[a-zA-Z]", number, position);
    }

    public static String characterOrInteger(String name, Number number, Position position)
    {
        return buildString(name, "[a-zA-Z0-9]", number, position);
    }

    public static String slug(String name, Number number, Position position)
    {
        return buildString(name, "[\\w-]", number, position);
    }

    private static String getNumberString(Number number)
    {
        switch (number) {
            case ONE:
                return "";
            case AT_LEAST_ONE:
                return "+";
            case ZERO_OR_MORE:
                return "*";
            case ONE_OR_NOT:
                return "?";
        }

        return "";
    }

    private static String buildString(String name, String pattern, Number number, Position position)
    {
        pattern = pattern + getNumberString(number);
        pattern = addPositionToPattern(pattern, position);

        return "(?<" + name + ">" + pattern + ")";
    }

    private static String addPositionToPattern(String pattern, Position position)
    {
        switch (position)
        {
            case BEGINNING:
                return "^" + pattern;
            case ENDING:
                return pattern + "$";
            case ELSEWHERE:
                return pattern;
        }

        return pattern;
    }

}
