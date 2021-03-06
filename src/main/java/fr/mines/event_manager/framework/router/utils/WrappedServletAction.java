package fr.mines.event_manager.framework.router.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

public class WrappedServletAction {
    HttpServletRequest request;

    HttpServletResponse response;

    Map<String, String> parameters = Collections.emptyMap();

    public WrappedServletAction(HttpServletRequest request, HttpServletResponse response, Map<String, String> parameters)
    {
        this.request = request;
        this.response = response;
        this.parameters = parameters;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public WrappedServletAction set(String key, Object value)
    {
        request.setAttribute(key, value);

        return this;
    }

    public String get(String key)
    {
        String parameter = this.request.getParameter(key);

        if (null != parameter)
        {
            return parameter;
        }

        return this.parameters.get(key);
    }

    public void add(String key, String value)
    {
        this.parameters.put(key, value);
    }
}