package fr.mines.event_manager.user.validator;

import fr.mines.event_manager.home_made_framework.repository.utils.Field;
import fr.mines.event_manager.home_made_framework.validator.AbstractValidator;
import fr.mines.event_manager.user.entity.User;
import fr.mines.event_manager.user.manager.UserManager;

import java.util.HashMap;
import java.util.Map;

public class UserValidator extends AbstractValidator<User> {
    @Override
    public Map<String, String> isValid(User entity) {
        HashMap<String, String> errMap = new HashMap<>();

        if (!isUnique(entity.getEmail()))
            errMap.put("email-unique", "Un utilisateur avec cette adresse email existe déjà");
        if (isNullOrEmpty(entity.getLastName()))
            errMap.put("last-name", "Veuillez entrer votre nom");
        if (isNullOrEmpty(entity.getFirstName()))
            errMap.put("first-name", "Veuillez entrer votre prénom");
        if (!isValidEmailAddress(entity.getEmail()))
            errMap.put("email", "Veuillez entrer une adresse mail valide");
        if (isNullOrEmpty(entity.getPassword()))
            errMap.put("password", "Veuillez entrer un mot de passe");
        if (isNullOrEmpty(entity.getCompany()))
            errMap.put("company", "Veuillez entrer votre société");
        return errMap;
    }

    protected boolean isUnique(String email)
    {
        return !UserManager
                .getInstance()
                .getRepository()
                .findSingleBy(new Field<String>("email", email, Field.Filter.EQUAL))
                .isPresent()
                ;
    }
}
