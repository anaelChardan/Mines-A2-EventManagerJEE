package fr.mines.event_manager.app.repository;

import fr.mines.event_manager.event.repository.EventRepository;
import fr.mines.event_manager.user.repository.UserRepository;

public class TankRepository {
    private static TankRepository instance = null;

    private UserRepository userRepository;
    private EventRepository eventRepository;

    private TankRepository()
    {
        this.userRepository = new UserRepository();
        this.eventRepository = new EventRepository();
    }

    public static TankRepository getInstance()
    {
        if (null == instance)
        {
            instance = new TankRepository();
        }

        return instance;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public EventRepository getEventRepository() {
        return eventRepository;
    }
}
