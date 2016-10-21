package fr.mines.event_manager.event.servlet;

import fr.mines.event_manager.framework.router.utils.PatternHelper;
import fr.mines.event_manager.framework.servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Pattern;

@WebServlet(name = "EventServlet", urlPatterns = {"/event/*"})
public class EventServlet extends BaseServlet {
    @Override
    protected Map<String, Pattern> initGetRoutes() {
        return new HashMap<String, Pattern>()
        {{
            put("index", PatternHelper.createPattern("/"));
            put("showOne", Pattern.compile("/(?<id>\\d+)"));
        }};
    }

    protected void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("I am in the listAll ");
    }

    protected void showOne( HttpServletRequest request, HttpServletResponse response, Map<String, String> parameters) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("I am in the listOne -> id =" + parameters.get("id"));
    }
}
