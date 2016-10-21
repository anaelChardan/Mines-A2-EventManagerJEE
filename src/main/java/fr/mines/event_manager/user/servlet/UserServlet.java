package fr.mines.event_manager.user.servlet;


import fr.mines.event_manager.framework.router.servlet.Servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@WebServlet(name = "UserServlet", urlPatterns = {"/user/*"})
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
