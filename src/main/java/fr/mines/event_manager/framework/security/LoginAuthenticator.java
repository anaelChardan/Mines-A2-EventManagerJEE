package fr.mines.event_manager.framework.security;

import fr.mines.event_manager.app.servlet.repository.TankRepository;
import fr.mines.event_manager.user.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Optional;

/**
 * Created by Damien on 21/10/2016.
 */
public class LoginAuthenticator {

    public static Optional<User> checkConnexion(HttpServletRequest request){


        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Vérification qu'il existe dans la base
        // return TankRepository.getInstance().getUserRepository().findByFields("");

        HashMap<String, String> mockUsers = new HashMap();
        mockUsers.put("Damien","test");

        if(mockUsers.containsKey(email)){
            if(mockUsers.get(email).equals(password)){
                return Optional.empty();
            }
        }
        return Optional.empty();



    }
}
