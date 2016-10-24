package fr.mines.event_manager.framework.router.utils;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class UtilException {

    @FunctionalInterface
    public interface ConsumerWithExceptions<T> {
        void accept(T t) throws Exception;
    }

    /**
     * .forEach(rethrowConsumer(name -> System.out.println(Class.forName(name)))); or .forEach(rethrowConsumer(ClassNameUtil::println));
     */
    public static <T> Consumer<T> rethrowConsumer(ConsumerWithExceptions<T> consumer) {
        return t -> {
            try {
                consumer.accept(t);
            } catch (Exception exception) {
                throwAsUnchecked(exception);
            }
        };
    }

    @SuppressWarnings("unchecked")
    private static <E extends Throwable> void throwAsUnchecked(Exception exception) throws E {
        throw (E) exception;
    }
}
