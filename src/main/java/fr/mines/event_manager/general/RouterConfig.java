package fr.mines.event_manager.general;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouterConfig {
    protected Map<String, List<String>> routes = new HashMap<>();
    protected Handler handler;

    public RouterConfig(Handler handler)
    {
        this.handler = handler;
        for(HttpWords method : HttpWords.values())
        {
            this.routes.put(method.name(), new ArrayList<>());
        }
    }

    protected void addRoute(HttpWords method, String route)
    {
        this.routes.get(method.name()).add(route);
    }

    protected void addRoutes(HttpWords method, List<String> routes)
    {
        routes.forEach(route -> this.addRoute(method, route));
    }

    protected boolean canHandle(HttpWords method, String route)
    {
        return this.routes.get(method.name()).contains(route);
    }

    protected void handle(HttpWords method, String route, HttpServletRequest request, HttpServletResponse response)
    {
        if (!this.canHandle(method, route))
        {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            return;
        }




//        return this.routes.get(method.name()).contains(route);
    }
}
