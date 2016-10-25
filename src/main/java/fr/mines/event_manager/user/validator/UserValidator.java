package fr.mines.event_manager.user.validator;

import fr.mines.event_manager.framework.validator.AbstractValidator;
import fr.mines.event_manager.user.entity.User;

import java.util.Collections;
import java.util.Map;

public class UserValidator extends AbstractValidator<User> {
    @Override
    public Map<String, String> isValid(User entity) {

        return Collections.emptyMap();
    }
}
