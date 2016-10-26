package fr.mines.event_manager.framework.validator;

import fr.mines.event_manager.framework.entity.AbstractEntity;
import fr.mines.event_manager.framework.entity.AbstractSelfManagedEntity;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

public abstract class AbstractValidator<T extends AbstractEntity> {

    public abstract Map<String, String> isValid(T entity);

    public boolean supports(AbstractSelfManagedEntity object)
    {
        try {
            if (object.getClass().equals(getClassType()))
            {
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    protected Class getClassType() throws ClassNotFoundException {
        return ((Class) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    protected T toWantedType(Object object)
    {
        return (T) object;
    }
}
