package fr.mines.event_manager.home.servlet;

import fr.mines.event_manager.framework.servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@WebServlet(name = "HomeServlet", urlPatterns = {"/"})
public class HomeServlet extends BaseServlet {
    @Override
    protected Map<String, Pattern> initGetRoutes() {
        return new HashMap<String, Pattern>()
        {{
            put("home", Pattern.compile("/"));
        }};
    }

    protected void home(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("home");
    }
}
