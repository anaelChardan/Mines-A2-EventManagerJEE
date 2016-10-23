package fr.mines.event_manager.event.servlet;

import fr.mines.event_manager.app.repository.TankRepository;
import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.framework.router.utils.PatternHelper;
import fr.mines.event_manager.framework.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
            put("eventForm", Pattern.compile("/eventForm"));
        }};
    }

    @Override
    protected Map<String, Pattern> initPostRoutes() {
        return new HashMap<String, Pattern>()
        {{
            put("newEvent", Pattern.compile("/newEvent"));
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

    protected void eventForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (null == session) {
            System.out.println("ici");
            this.render("login.jsp", request, response);
            return;
        }

        this.render("/event/createEvent.jsp",request,response);
    }

    protected void newEvent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (null == session) {
            System.out.println("ici");
            this.render("login.jsp", request, response);
            return;
        }

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String start_date = request.getParameter("start_date");
        String end_date = request.getParameter("end_date");
        System.out.println(start_date);
        int maxTickets = Integer.parseInt(request.getParameter("max_tickets"));
        Double price = Double.parseDouble(request.getParameter("price"));

        Event event = new Event();

        this.render("/event/createEvent.jsp",request,response);
    }
}
