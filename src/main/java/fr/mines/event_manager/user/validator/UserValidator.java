package fr.mines.event_manager.user.validator;

import fr.mines.event_manager.framework.validator.AbstractValidator;
import fr.mines.event_manager.user.entity.User;

public class UserValidator extends AbstractValidator<User> {
    @Override
    public boolean isValid(User entity) {

        return false;
    }
}
