package fr.mines.event_manager.core.servlet;

import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.event.manager.EventManager;
import fr.mines.event_manager.framework.repository.JPAEntityManagerWrapper;
import fr.mines.event_manager.framework.router.http.ComputedRoute;
import fr.mines.event_manager.framework.router.servlet.Servlet;
import fr.mines.event_manager.framework.router.utils.WrappedServletAction;
import fr.mines.event_manager.framework.security.UserProvider;
import fr.mines.event_manager.framework.session.SessionManager;
import fr.mines.event_manager.framework.utils.Alert;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

public class BaseServlet extends Servlet {
    protected void render(String jspPage, WrappedServletAction action, Alert alert) throws ServletException, IOException {
        HttpServletRequest request = action.getRequest();
        HttpServletResponse response = action.getResponse();

        request.setAttribute("jspPage", jspPage);
        request.setAttribute("IS_LOGGED", UserProvider.isConnected(action.getRequest()));
        request.setAttribute("CURRENT_USER", UserProvider.getCurrentUser(action.getRequest()));

        if (isNull(alert)) {
            alert = SessionManager.getAndRemove(request, "ALERT");
        }

        if (!isNull(alert)) {
            request.setAttribute("alertTypes", alert.getType().equals(Alert.TYPE.DANGER) ? "danger" : "success");
            request.setAttribute("alertMessages", alert.getMessages());
        }

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp");

        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            response.sendError(SC_INTERNAL_SERVER_ERROR);
        }
    }

    protected void render(String jspPage, WrappedServletAction action) throws ServletException, IOException {
        render(jspPage, action, null);
    }

    @Override
    protected void redirectConnectedProtectionRoute(WrappedServletAction action, ComputedRoute route) throws IOException, ServletException {
        if (UserProvider.isConnected(action.getRequest())){
            route.consume(action);
            return;
        }
        String servletPath = action.getRequest().getServletPath();
        String pathInfo = action.getRequest().getPathInfo();
        String path = (null == pathInfo ? servletPath : servletPath+pathInfo);

        String fullPath = "/app/login?path=" + path;

        if (!("/".equals(path))) {
            this.redirect(action, fullPath, new Alert(Alert.TYPE.DANGER, "Vous devez être connecté pour accèder à la page demandée"));
            return;
        }

        this.redirect(action,fullPath);
    }

    @Override
    protected void redirectProprietaryProtectionRoute(WrappedServletAction action, ComputedRoute route) throws IOException, ServletException {
        String servletPath = action.getRequest().getServletPath();
        if ("/event".equals(servletPath))
        {
            Optional<Event> eventOptional = EventManager.getInstance().find((Integer.parseInt(action.getParameters().get("id"))));
            if (!eventOptional.isPresent())
            {
                this.redirect(action, "/event/", new Alert(Alert.TYPE.DANGER, "L'événement n'existe pas"));
                return;
            }

            Event event = eventOptional.get();
            if (Objects.equals(event.getAuthor().getId(), UserProvider.getCurrentUser(action.getRequest()).getId()))
            {
                route.consume(action);
                return;
            }

            this.redirect(action, "/event/", new Alert(Alert.TYPE.DANGER, "Vous ne pouvez pas modifier cet évènement"));
        }
    }

    protected void redirect(WrappedServletAction action, String endPoint, Alert alert) throws IOException {
        if (!isNull(alert)) SessionManager.set(action.getRequest(), "ALERT", alert);
        action.getResponse().sendRedirect(getServletContext().getContextPath() + endPoint);
    }

    protected void redirect(WrappedServletAction action, String endPoint) throws IOException {
        redirect(action, endPoint, null);
    }

    @Override
    public void destroy() {
        super.destroy();
        JPAEntityManagerWrapper.getInstance().close();
    }
}
