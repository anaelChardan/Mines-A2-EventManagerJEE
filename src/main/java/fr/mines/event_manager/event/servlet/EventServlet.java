package fr.mines.event_manager.event.servlet;

import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.framework.router.http.Route;
import fr.mines.event_manager.core.servlet.BaseServlet;

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
    protected Set<Route> initGetRoutes() {
        return new HashSet<Route>()
        {{
            add(new Route("index", Pattern.compile("/"), Route.PROTECTION_LEVEL.CONNECTED));
            add(new Route("showOne", Pattern.compile("/(?<id>\\d+)"), Route.PROTECTION_LEVEL.CONNECTED));
            add(new Route("eventForm", Pattern.compile("/new"), Route.PROTECTION_LEVEL.CONNECTED));
        }};
    }

    @Override
    protected Set<Route> initPostRoutes() {
        return new HashSet<Route>()
        {{
            add(new Route("newEvent", Pattern.compile("/newEvent"), Route.PROTECTION_LEVEL.CONNECTED));
        }};
    }

    //
    protected void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("I am in the listAll ");
    }

    protected void showOne(HttpServletRequest request, HttpServletResponse response, Map<String, String> parameters) throws IOException {
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
