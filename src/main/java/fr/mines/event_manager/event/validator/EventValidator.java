package fr.mines.event_manager.event.validator;

import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.framework.validator.AbstractValidator;

public class EventValidator extends AbstractValidator<Event> {
    @Override
    public boolean isValid(Event entity) {
        System.out.println("COOUCOU JE SUIS DANS LEVENT VALIDATOR");
        return false;
    }
}
