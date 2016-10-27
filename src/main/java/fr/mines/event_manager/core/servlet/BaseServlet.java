package fr.mines.event_manager.core.servlet;

import fr.mines.event_manager.app.repository.TankRepository;
import fr.mines.event_manager.framework.entity.AbstractUser;
import fr.mines.event_manager.framework.repository.utils.Field;
import fr.mines.event_manager.framework.router.http.ComputedRoute;
import fr.mines.event_manager.framework.router.servlet.Servlet;
import fr.mines.event_manager.framework.router.utils.WrappedServletAction;
import fr.mines.event_manager.framework.security.UserProvider;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseServlet extends Servlet {
    protected void render(String jspPage, WrappedServletAction action) throws ServletException, IOException {
        action.getRequest().setAttribute("jspPage", jspPage);
        action.getRequest().setAttribute("IS_LOGGED", UserProvider.isConnected(action.getRequest()));
        action.getRequest().setAttribute("CURRENT_USER", UserProvider.getCurrentUser(action.getRequest()));

        getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(action.getRequest(), action.getResponse());
    }

    @Override
    protected void redirectConnectedProtectionRoute(WrappedServletAction action, ComputedRoute route) throws IOException, ServletException {
        if (UserProvider.isConnected(action.getRequest())){
            route.consume(action);
            return;
        }
        String servletPath = action.getRequest().getServletPath();
        String pathInfo = action.getRequest().getPathInfo();
        String path = null == pathInfo ? servletPath : servletPath+pathInfo;
        this.redirect(action.getResponse(),"/app/login?path="+path);
    }

    @Override
    protected void redirectProprietaryProtectionRoute(WrappedServletAction action, ComputedRoute route) {

    }

    protected boolean connect(HttpServletRequest request)
    {
        return UserProvider.connect(request, () -> TankRepository.getInstance().getUserRepository().findSingleBy(
                new Field<String>("email", request.getParameter("email"), Field.Filter.EQUAL),
                new Field<String>("password", request.getParameter("password"), Field.Filter.EQUAL)
            ).map(AbstractUser.class::cast)
        );
    }

    protected void redirect(HttpServletResponse response, String endPoint) throws IOException {
        response.sendRedirect(this.getServletContext().getContextPath() + endPoint);
    }
}
