package fr.mines.event_manager.general;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public abstract class Servlet extends HttpServlet {
    RouterConfig routerConfig;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); // NB: Indispensable !!!
        this.routerConfig = new RouterConfig(this.getHandler());
        this.initRoutes();
    }

    protected abstract Handler getHandler();

    protected abstract List<String> initGetRoutes();

    protected abstract List<String> initPostRoutes();

    protected abstract List<String> initPutRoutes();

    protected abstract List<String> initDeleteRoutes();

    protected void initRoutes()
    {
        this.routerConfig.addRoutes(HttpWords.GET, this.initGetRoutes());
        this.routerConfig.addRoutes(HttpWords.POST, this.initPostRoutes());
        this.routerConfig.addRoutes(HttpWords.PUT, this.initPutRoutes());
        this.routerConfig.addRoutes(HttpWords.DELETE, this.initDeleteRoutes());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        process(HttpWords.GET, request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        process(HttpWords.POST, request, response);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        process(HttpWords.POST, request, response);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        process(HttpWords.POST, request, response);
    }

    protected void process(HttpWords method, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}