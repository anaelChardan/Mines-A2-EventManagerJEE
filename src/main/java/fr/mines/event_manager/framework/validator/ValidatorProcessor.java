package fr.mines.event_manager.framework.validator;

import fr.mines.event_manager.event.validator.EventValidator;
import fr.mines.event_manager.framework.entity.AbstractEntity;
import fr.mines.event_manager.user.validator.UserValidator;

import java.util.ArrayList;
import java.util.List;

public class ValidatorProcessor {
    private static ValidatorProcessor instance = null;

    List<AbstractValidator> validators = new ArrayList<>();

    private ValidatorProcessor()
    {
        validators.add(new EventValidator());
        validators.add(new UserValidator());
    }

    public static ValidatorProcessor getInstance()
    {
        if (null == instance)
        {
            instance = new ValidatorProcessor();
        }

        return instance;
    }

    public boolean isValid(AbstractEntity object)
    {
        for (AbstractValidator validator: validators)
        {
            if (validator.supports(object))
            {
                validator.isValid(validator.toWantedType(object));
            }
        }
        return false;
    }
}
