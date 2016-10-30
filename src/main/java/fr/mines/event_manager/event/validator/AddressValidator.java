package fr.mines.event_manager.event.validator;

import fr.mines.event_manager.event.entity.Address;
import fr.mines.event_manager.framework.validator.AbstractValidator;

import java.util.HashMap;
import java.util.Map;

public class AddressValidator extends AbstractValidator<Address> {
    @Override
    public Map<String, String> isValid(Address entity) {
        HashMap<String,String> errMap = new HashMap<>();

        if (null == entity.getAddress1() || entity.getAddress1().isEmpty())
            errMap.put("address1","Veuillez entrer une adresse valide");
        if (null == entity.getZipCode() || entity.getZipCode().isEmpty())
            errMap.put("zip_code","Veuillez entrer un code postal");
        if (null == entity.getCity() || entity.getCity().isEmpty())
            errMap.put("city","Veuillez entrer une ville");
        if (null == entity.getCountry() || entity.getCountry().isEmpty())
            errMap.put("country","Veuillez entrer un pays");

        return errMap;

    }
}
