package fr.mines.event_manager.framework.validator;

import fr.mines.event_manager.event.entity.Address;
import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.event.validator.AddressValidator;
import fr.mines.event_manager.event.validator.EventValidator;
import fr.mines.event_manager.framework.entity.AbstractEntity;
import fr.mines.event_manager.user.entity.User;
import fr.mines.event_manager.user.validator.UserValidator;

import java.util.HashMap;
import java.util.Map;

public class ValidatorProcessor {
    private static ValidatorProcessor instance = null;

    Map<Class, AbstractValidator> validators = new HashMap<>();

    private ValidatorProcessor()
    {
        validators.put(Event.class, new EventValidator());
        validators.put(User.class, new UserValidator());
        validators.put(Address.class, new AddressValidator());
    }

    public static ValidatorProcessor getInstance()
    {
        if (null == instance)
        {
            instance = new ValidatorProcessor();
        }

        return instance;
    }

    public Map<String,String> isValid(AbstractEntity object)
    {
        AbstractValidator validator = validators.get(object.getClass());
        return validator.isValid(validator.toWantedType(object));
    }
}
