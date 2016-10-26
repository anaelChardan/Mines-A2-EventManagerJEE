package fr.mines.event_manager.event.manager;

import fr.mines.event_manager.event.entity.Address;
import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.framework.manager.BaseEntityManager;
import fr.mines.event_manager.framework.security.UserProvider;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Damien on 24/10/2016.
 */
public class AddressManager implements BaseEntityManager<Address> {

    private static AddressManager instance = null;

    private AddressManager()
    {}

    public static AddressManager getInstance()
    {
        if (null == instance)
        {
            instance = new AddressManager();
        }

        return instance;
    }

    @Override
    public Address create(HttpServletRequest request) {
        // Gestion de l'adresse
        return (new Address())
                .setAddress1(request.getParameter("address1"))
                .setAddress2(request.getParameter("address2"))
                .setZipCode(request.getParameter("zip_code"))
                .setCity(request.getParameter("city"))
                .setCountry(request.getParameter("country"));
    }

    @Override
    public Address persist(Address object) {
        return null;
    }


}
