package fr.mines.event_manager.home_made_framework.validator;

import fr.mines.event_manager.home_made_framework.entity.AbstractEntity;
import fr.mines.event_manager.home_made_framework.entity.AbstractSelfManagedEntity;

import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    protected boolean isNullOrEmpty(String value)
    {
        return null == value || value.isEmpty();
    }

    public boolean isValidEmailAddress(String email) {
        if (isNullOrEmpty(email))
        {
            return false;
        }
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = java.util.regex.Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
