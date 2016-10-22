package fr.mines.event_manager.user.servlet;


import fr.mines.event_manager.app.repository.TankRepository;
import fr.mines.event_manager.framework.router.servlet.Servlet;
import fr.mines.event_manager.framework.servlet.BaseServlet;
import fr.mines.event_manager.user.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@WebServlet(name = "UserServlet", urlPatterns = {"/user/*"})
public class UserServlet extends BaseServlet {
    @Override
    protected Map<String, Pattern> initGetRoutes() {
        return new HashMap<String, Pattern>()
        {{
            put("subscribe", Pattern.compile("/subscribe/(?<name>\\w+)"));
            put("consult", Pattern.compile("/consult"));
        }};
    }

    protected void subscribe(HttpServletRequest request, HttpServletResponse response, Map<String, String> parameters) throws IOException {
        response.getWriter().println("Hello " + parameters.get("name"));
    }

    protected void consult (HttpServletRequest request, HttpServletResponse response, Map<String, String> parameters) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if(session==null){
            System.out.println("ici");
            this.render("login.jsp", request, response);
            return;
        }
        Optional<User> user = TankRepository.getInstance().getUserRepository().find((Integer)session.getAttribute("id"));
        if(user.isPresent()){
            User usr = user.get();
            request.setAttribute("firstName",usr.getFirstName());
            request.setAttribute("lastName",usr.getLastName());
            request.setAttribute("email",usr.getEmail());
        }
        this.render("consultUser", request, response);
    }
}
