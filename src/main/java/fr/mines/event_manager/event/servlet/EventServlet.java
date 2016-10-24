package fr.mines.event_manager.event.servlet;

import fr.mines.event_manager.app.repository.TankRepository;
import fr.mines.event_manager.core.http.Paths;
import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.event.manager.EventManager;
import fr.mines.event_manager.framework.router.http.Route;
import fr.mines.event_manager.core.servlet.BaseServlet;
import fr.mines.event_manager.framework.router.utils.WrappedServletAction;
import fr.mines.event_manager.framework.validator.ValidatorProcessor;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "EventServlet", urlPatterns = {"/event/*"})
public class EventServlet extends BaseServlet {
    @Override
    protected Set<Route> initGetRoutes() {
        Set<Route> routes = new HashSet<>();

        routes.add(Paths.getIndexEvent(this::index));
        routes.add(Paths.getOneEvent(this::showOne));
        routes.add(Paths.getCreateEvent(this::newEventForm));

        return routes;
    }

    @Override
    protected Set<Route> initPostRoutes() {
        Set<Route> routes = new HashSet<>();
        routes.add(Paths.postCreateEvent(this::eventPost));
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

    protected void newEventForm(WrappedServletAction action) throws ServletException, IOException {
        HttpSession session = action.getRequest().getSession(false);
        if (null == session) {
            System.out.println("ici");
            this.render("login.jsp", action.getRequest(), action.getResponse());
            return;
        }
        this.render("/event/createEvent.jsp",action.getRequest(),action.getResponse());
    }

    protected void eventPost(WrappedServletAction action) throws ServletException, IOException {
        HttpSession session = action.getRequest().getSession(false);
        if (null == session) {
            System.out.println("ici");
            this.render("login.jsp", action.getRequest(), action.getResponse());
            return;
        }

        Event event = EventManager.getInstance().create(action.getRequest());
        ValidatorProcessor.getInstance().isValid(event);


        this.render("/event/createEvent.jsp",action.getRequest(),action.getResponse());
    }

}
