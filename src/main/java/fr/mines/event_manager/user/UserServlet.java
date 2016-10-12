package fr.mines.event_manager.user;


import fr.chardan.jee.servlet.router.Servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class UserServlet extends Servlet {
    @Override
    protected Map<String, Pattern> initGetRoutes() {
        return new HashMap<String, Pattern>()
        {{
            put("subscribe", Pattern.compile("/subscribe/(?<name>\\w+)"));
        }};
    }

    protected void subscribe(HttpServletRequest request, HttpServletResponse response, Map<String, String> parameters) throws IOException {
        response.getWriter().println("Hello " + parameters.get("name"));
    }
}
