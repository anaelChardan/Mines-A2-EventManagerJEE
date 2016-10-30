package fr.mines.event_manager.user.manager;


import fr.mines.event_manager.home_made_framework.manager.BaseEntityManager;
import fr.mines.event_manager.home_made_framework.repository.utils.Field;
import fr.mines.event_manager.user.entity.User;
import fr.mines.event_manager.user.repository.UserRepository;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created by Damien on 24/10/2016.
 */
public class UserManager implements BaseEntityManager<User> {

    private static UserManager instance = null;
    private UserRepository repository = new UserRepository();

    private UserManager() {
    }

    public static synchronized UserManager getInstance() {
        if (null == instance) {
            instance = new UserManager();
        }
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

    public User update(User object) {
        return repository.update(object);
    }

    public Optional<User> find(int id) {
        return repository.find(id);
    }

    public Optional<User> findByEmailAndPassword(String email, String password)
    {
        return repository.findSingleBy(
                new Field<String>("email", email, Field.Filter.EQUAL),
                new Field<String>("password", password, Field.Filter.EQUAL)
        );
    }

    public UserRepository getRepository() {
        return this.repository;
    }

    public void close()
    {
        repository.close();
    }

}
