package fr.mines.event_manager.app.servlet;

import fr.mines.event_manager.framework.servlet.BaseServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class AppServlet extends BaseServlet {
    @Override
    protected Map<String, Pattern> initGetRoutes() {
        return new HashMap<String, Pattern>()
        {{
            put("login", Pattern.compile("/login"));
        }};
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.render("login.jsp", request, response);
    }
}
