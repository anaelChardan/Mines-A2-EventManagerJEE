package fr.mines.event_manager.framework.validator;

import fr.mines.event_manager.framework.entity.AbstractEntity;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractValidator<T extends AbstractEntity> {

    public abstract boolean isValid(T entity);

    public boolean supports(AbstractEntity object)
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
