package fr.mines.event_manager.user;

import fr.mines.event_manager.general.Handler;
import fr.mines.event_manager.general.Servlet;

import java.util.Arrays;
import java.util.List;

/**
 * Created by nanou on 09/10/2016.
 */
public class UserServlet extends Servlet {

    @Override
    protected Handler getHandler() {
        return new UserHandler();
    }

    @Override
    protected List<String> initGetRoutes() {
        return Arrays.asList("list", "new", "edit", "show");
    }

    @Override
    protected List<String> initPostRoutes() {
        return null;
    }

    @Override
    protected List<String> initPutRoutes() {
        return null;
    }

    @Override
    protected List<String> initDeleteRoutes() {
        return null;
    }


}
