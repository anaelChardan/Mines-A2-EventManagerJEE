package fr.mines.event_manager.user.servlet;


import fr.mines.event_manager.app.repository.TankRepository;
import fr.mines.event_manager.framework.router.http.Route;
import fr.mines.event_manager.core.servlet.BaseServlet;
import fr.mines.event_manager.user.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

@WebServlet(name = "UserServlet", urlPatterns = {"/user/*"})
public class UserServlet extends BaseServlet {
    @Override
    protected Set<Route> initGetRoutes() {
        return new HashSet<Route>() {{
            add(new Route("profile", Pattern.compile("/profile"), Route.PROTECTION_LEVEL.CONNECTED));
        }};
    }

    protected void profile(HttpServletRequest request, HttpServletResponse response, Map<String, String> parameters) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (null == session) {
            System.out.println("ici");
            this.render("login.jsp", request, response);
            return;
        }

        Optional<User> user = TankRepository.getInstance().getUserRepository().find((Integer) session.getAttribute("id"));

        if (!user.isPresent())
        {
            //FAIRE UNE 500 -> lutilisateur en session n'existe pas
            return;
        }

        User usr = user.get();
        request.setAttribute("firstName", usr.getFirstName());
        request.setAttribute("lastName", usr.getLastName());
        request.setAttribute("email", usr.getEmail());
        this.render("user/consultUser.jsp", request, response);
    }
}
