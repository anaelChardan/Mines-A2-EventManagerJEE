package fr.mines.event_manager.event.validator;

import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.framework.validator.AbstractValidator;
import fr.mines.event_manager.framework.validator.ValidatorProcessor;

import java.util.HashMap;

public class EventValidator extends AbstractValidator<Event> {
    @Override
    public boolean isValid(Event entity) {
        System.out.println("COOUCOU JE SUIS DANS LEVENT VALIDATOR");

        HashMap<String,String> errMap = new HashMap<>();

        if (null == entity.getName())
            errMap.put("name","Veuillez entrer un nom d'événement");
        if (null == entity.getStartDate())
            errMap.put("start_date","Veuillez entrer une date de début");
        if (null == entity.getEndDate())
            errMap.put("end_date","Veuillez entrer une date de fin");
        if (null == entity.getPrice())
            errMap.put("price","Veuillez entrer un prix");
        if (null == entity.getMaxTickets())
            errMap.put("max_tickets","Veuillez entrer un nombre de places");
        if (null == entity.getDescription())
            errMap.put("description","Veuillez entrer une description");
//        if (ValidatorProcessor.getInstance().isValid(entity.getAddress()));





        return false;
    }
}
