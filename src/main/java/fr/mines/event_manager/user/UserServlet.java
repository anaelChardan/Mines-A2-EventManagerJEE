package fr.mines.event_manager.user;


import fr.chardan.jee.servlet.router.Servlet;
import fr.mines.event_manager.front.BaseServlet;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class UserServlet extends BaseServlet {
    @Override
    protected Map<String, Pattern> initGetRoutes() {
        return new HashMap<String, Pattern>()
        {{
            put("login", Pattern.compile("/"));
            put("listOne", Pattern.compile("/(?<id>\\d+)/(?<name>\\w+)"));
        }};
    }
}
