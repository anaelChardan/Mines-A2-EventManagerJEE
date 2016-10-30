package fr.mines.event_manager.event.validator;

import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.home_made_framework.validator.AbstractValidator;
import fr.mines.event_manager.home_made_framework.validator.ValidatorProcessor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EventValidator extends AbstractValidator<Event> {
    @Override
    public Map<String, String> isValid(Event entity) {
        HashMap<String,String> errMap = new HashMap<>();

        if (null == entity.getName() || entity.getName().isEmpty())
            errMap.put("name","Veuillez entrer un nom d'événement");
        if (null == entity.getStartDate())
            errMap.put("start_date","Veuillez entrer une date de début");
        else if (entity.getStartDate().before(new Date()))
            errMap.put("start_date","La date de début ne peut pas être inférieure à la date du jour");
        if (null == entity.getEndDate())
            errMap.put("end_date","Veuillez entrer une date de fin");
        else if (entity.getEndDate().before(entity.getStartDate()))
            errMap.put("end_date","La date de fin doit être supérieure à la date de début");
        if (null == entity.getPrice() || entity.getPrice() < 0)
            errMap.put("price","Veuillez entrer un prix correct (Supérieur ou égal à 0)");
        if (null == entity.getMaxTickets() || entity.getMaxTickets() < 0)
            errMap.put("max_tickets","Veuillez entrer un nombre de places valide");
        if (null == entity.getDescription() || entity.getDescription().isEmpty())
            errMap.put("description","Veuillez entrer une description");
        errMap.putAll(ValidatorProcessor.getInstance().isValid(entity.getAddress()));

        return errMap;
    }
}
