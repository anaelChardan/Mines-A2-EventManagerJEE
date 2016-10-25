package fr.mines.event_manager.event.servlet;

import fr.mines.event_manager.app.repository.TankRepository;
import fr.mines.event_manager.core.http.Paths;
import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.framework.repository.Field;
import fr.mines.event_manager.framework.router.http.Route;
import fr.mines.event_manager.core.servlet.BaseServlet;
import fr.mines.event_manager.framework.router.utils.WrappedServletAction;
import fr.mines.event_manager.user.entity.User;

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
        Set<Route> routes = new HashSet<>();

        routes.add(Paths.getIndexEvent(this::index));
        routes.add(Paths.getOneEvent(this::showOne));
        routes.add(Paths.getCreateEvent(this::newEvent));

        return routes;
    }

    @Override
    protected Set<Route> initPostRoutes() {
        Set<Route> routes = new HashSet<>();

        routes.add(Paths.postCreateEvent(this::eventForm));

        return routes;
    }

    //
    protected void index(WrappedServletAction action) throws IOException {
        PrintWriter out = action.getResponse().getWriter();
        System.out.println("coucou maggle");
    }

    protected void showOne(WrappedServletAction action) throws IOException {
        PrintWriter out = action.getResponse().getWriter();
        out.println("I am in the listOne -> id =" + action.getParameters().get("id"));

        Optional<Event> event = TankRepository.getInstance().getEventRepository().find(Integer.parseInt(action.getParameters().get("id")));
        if (event.isPresent())
        {
            out.println("I am in the listOne -> id =" + event.get().getName());
            return;
        }

            out.println("Yen a pas");
    }

    protected void eventForm(WrappedServletAction action) throws ServletException, IOException {
        HttpSession session = action.getRequest().getSession(false);
        if (null == session) {
            System.out.println("ici");
            this.render("login.jsp", action.getRequest(), action.getResponse());
            return;
        }

        this.render("/event/createEvent.jsp",action.getRequest(),action.getResponse());
    }

    protected void newEvent(WrappedServletAction action) throws ServletException, IOException {
        HttpSession session = action.getRequest().getSession(false);
        if (null == session) {
            System.out.println("ici");
            this.render("login.jsp", action.getRequest(), action.getResponse());
            return;
        }

        String name = action.getRequest().getParameter("name");
        String description = action.getRequest().getParameter("description");
        String start_date = action.getRequest().getParameter("start_date");
        String end_date = action.getRequest().getParameter("end_date");
        System.out.println(start_date);
        int maxTickets = Integer.parseInt(action.getRequest().getParameter("max_tickets"));
        Double price = Double.parseDouble(action.getRequest().getParameter("price"));

        Event event = new Event();

        this.render("/event/createEvent.jsp",action.getRequest(),action.getResponse());
    }
}
