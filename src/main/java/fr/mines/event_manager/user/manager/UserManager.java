package fr.mines.event_manager.user.manager;

import fr.mines.event_manager.app.repository.TankRepository;
import fr.mines.event_manager.event.entity.Event;
import fr.mines.event_manager.event.manager.AddressManager;
import fr.mines.event_manager.event.repository.EventRepository;
import fr.mines.event_manager.framework.manager.BaseEntityManager;
import fr.mines.event_manager.framework.security.UserProvider;
import fr.mines.event_manager.user.entity.User;
import fr.mines.event_manager.user.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * Created by Damien on 24/10/2016.
 */
public class UserManager implements BaseEntityManager<User> {

    private static UserManager instance = null;
    private UserRepository repository = new UserRepository();

    private UserManager() {
    }

    public static UserManager getInstance() {
        if (null == instance)
            instance = new UserManager();
        return instance;
    }

    @Override
    public User create(HttpServletRequest request) {
        return (new User())
                .setLastName(request.getParameter("last-name"))
                .setFirstName(request.getParameter("first-name"))
                .setEmail(request.getParameter("email"))
                .setPassword(request.getParameter("password"))
                .setCompany(request.getParameter("company"))
                ;
    }

    @Override
    public User persist(User object) {
        return repository.create(object);
    }

    public User update(User object)
    {
        return repository.update(object);
    }

    public Optional<User> find(int id)
    {
        return repository.find(id);
    }

    public UserRepository getRepository()
    {
        return this.repository;
    }

}
