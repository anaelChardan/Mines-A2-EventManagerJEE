package fr.mines.event_manager.event.validator;

import fr.mines.event_manager.event.entity.Address;
import fr.mines.event_manager.framework.validator.AbstractValidator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AddressValidator extends AbstractValidator<Address> {
    @Override
    public Map<String, String> isValid(Address entity) {
        HashMap<String,String> errMap = new HashMap<>();

        if (null == entity.getAddress1())
            errMap.put("address1","Veuillez entrer une adresse correcte");
        if (null == entity.getZipCode())
            errMap.put("zip_code","Veuillez entrer un code postal");
        if (null == entity.getCity())
            errMap.put("city","Veuillez entrer une ville");
        if (null == entity.getCountry())
            errMap.put("address1","Veuillez entrer un pays");

        return errMap;

    }
}
