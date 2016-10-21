package fr.mines.event_manager.app.servlet;

import fr.mines.event_manager.framework.security.LoginAuthenticator;
import fr.mines.event_manager.event.repository.EventRepository;
import fr.mines.event_manager.framework.servlet.BaseServlet;
import fr.mines.event_manager.user.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

public class AppServlet extends BaseServlet {
    @Override
    protected Map<String, Pattern> initGetRoutes() {
        return new HashMap<String, Pattern>() {{
            put("login", Pattern.compile("/login"));
        }};
    }

    @Override
    protected Map<String, Pattern> initPostRoutes() {
        return new HashMap<String, Pattern>() {{
            put("loginPost", Pattern.compile("/loginpost"));
        }};
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EventRepository eventRepository = new EventRepository();
        this.render("login.jsp", request, response);

    }

    protected void loginPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        Optional<User> user = LoginAuthenticator.checkConnexion(request);
        if (user.isPresent()) {
            HttpSession session = request.getSession();
            session.setAttribute("id", user.get().getId());
            this.render("home.jsp", request, response);
            return;
        }

        String errorMessage = "L'adresse mail et/ou le mot de passe ne sont pas valides";
        request.setAttribute("errorMessage", errorMessage);
        this.render("login.jsp", request, response);


    }


}
