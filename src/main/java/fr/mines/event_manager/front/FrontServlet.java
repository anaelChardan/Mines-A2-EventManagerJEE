package fr.mines.event_manager.front;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FrontServlet extends BaseServlet {
    @Override
    protected Map<String, Pattern> initGetRoutes() {
        return new HashMap<String, Pattern>()
        {{
            put("home", Pattern.compile("/"));
            put("login", Pattern.compile("/login"));
        }};
    }

    protected void home(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("home");
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("login");
    }

    void render(String jspPage, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("jspPage", jspPage);
        getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}
