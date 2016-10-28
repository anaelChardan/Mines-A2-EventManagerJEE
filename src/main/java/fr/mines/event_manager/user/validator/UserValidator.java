package fr.mines.event_manager.user.validator;

import fr.mines.event_manager.framework.validator.AbstractValidator;
import fr.mines.event_manager.user.entity.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserValidator extends AbstractValidator<User> {
    @Override
    public Map<String, String> isValid(User entity) {
        HashMap<String,String> errMap = new HashMap<>();

        if (null == entity.getLastName() || entity.getLastName().isEmpty())
            errMap.put("last-name","Veuillez entrer votre nom");
        if (null == entity.getFirstName() || entity.getFirstName().isEmpty())
            errMap.put("first-name","Veuillez entrer votre prénom");
        if (null == entity.getEmail() || entity.getEmail().isEmpty())
            errMap.put("email","Veuillez entrer une adresse mail valide");
        if (null == entity.getPassword() || entity.getPassword().isEmpty())
            errMap.put("password","Veuillez entrer un mot de passe");
        
        return errMap;
    }
}
