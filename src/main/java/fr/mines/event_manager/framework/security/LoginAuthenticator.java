package fr.mines.event_manager.framework.security;

import fr.mines.event_manager.app.servlet.repository.TankRepository;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Damien on 21/10/2016.
 */
public class LoginAuthenticator {

    public boolean checkConnexion(HttpServletRequest request){

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        // Vérification qu'il existe dans la base


    }
}
