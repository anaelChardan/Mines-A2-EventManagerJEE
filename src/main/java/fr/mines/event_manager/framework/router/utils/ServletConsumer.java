package fr.mines.event_manager.framework.router.utils;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@FunctionalInterface
public interface ServletConsumer<T> {
    void accept(T t) throws ServletException, IOException;
}
