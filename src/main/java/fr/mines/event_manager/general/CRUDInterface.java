package fr.mines.event_manager.general;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by nanou on 09/10/2016.
 */
public interface CRUDInterface {
    public void create(HttpServletRequest request, HttpServletResponse response);
    public void read(HttpServletRequest request, HttpServletResponse response);
    public void update(HttpServletRequest request, HttpServletResponse response);
    public void delete(HttpServletRequest request, HttpServletResponse response);
}
