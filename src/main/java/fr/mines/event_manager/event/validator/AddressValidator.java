package fr.mines.event_manager.event.validator;

import fr.mines.event_manager.event.entity.Address;
import fr.mines.event_manager.framework.validator.AbstractValidator;

public class AddressValidator extends AbstractValidator<Address> {
    @Override
    public boolean isValid(Address entity) {
        return false;
    }
}
