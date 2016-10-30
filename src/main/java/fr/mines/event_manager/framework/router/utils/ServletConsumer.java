package fr.mines.event_manager.framework.router.utils;

import javax.servlet.ServletException;
import java.io.IOException;

@FunctionalInterface
public interface ServletConsumer<T> {
    void accept(T t) throws ServletException, IOException;
}
