package fr.mines.event_manager.app.servlet;

import fr.mines.event_manager.framework.fixtures.Populator;
import fr.mines.event_manager.framework.servlet.BaseServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    @Override
    protected Map<String, Pattern> initPostRoutes() {
        return new HashMap<String, Pattern>()
        {{
            put("loginPost", Pattern.compile("/loginpost"));
        }};
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Populator.getInstance().populate();
        this.render("login.jsp", request, response);

    }

    protected void loginPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        System.out.println("TADA");
        // On récupère les attributs

        String username = request.getParameter("email");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");




        HashMap<String, String> mockUsers = new HashMap();
        mockUsers.put("Damien","test");

        String errorMessage = "";
        if(mockUsers.containsKey(username)){
            if(mockUsers.get(username).equals(password)){
                HttpSession session = request.getSession();
                session.setAttribute(username,password);
                this.render("home.jsp",request,response);
            }
            else{
                errorMessage = "Le mot de passe n'est pas valide";
            }
        }else{
            errorMessage = "L'adresse mail et/ou le mot de passe ne sont pas valides";
        }
        request.setAttribute("errorMessage",errorMessage);
        this.render("login.jsp", request, response);

    }



}
