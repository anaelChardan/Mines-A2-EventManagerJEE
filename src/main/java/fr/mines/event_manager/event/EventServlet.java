package fr.mines.event_manager.event;

import fr.mines.event_manager.general.HttpWords;
import fr.mines.event_manager.general.Servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Pattern;

public class EventServlet extends Servlet {
    @Override
    protected Map<String, Pattern> initGetRoutes() {
        return new HashMap<String, Pattern>()
        {{
            put("listAll", Pattern.compile("/"));
            put("listOne", Pattern.compile("/(?<id>\\d+)"));
        }};
    }

    protected void listAll(String path, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        out.println("Je suis bien dans le findAll ");
    }

    protected void listOne(String path, HttpServletResponse response) throws IOException {
        Map<String, String> parameters = this.extractParameters(HttpWords.GET, path, "listOne", "id");
        PrintWriter out = response.getWriter();
        out.println("Je suis dans le listOne -> id =" + parameters.get("id"));
    }
}
