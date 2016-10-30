package fr.mines.event_manager.framework.tags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PathTag extends SimpleTagSupport {
    private String endpoint;

    @Override
    public void doTag() throws JspException {
        PageContext pageContext = (PageContext) getJspContext();
        try {
            getJspContext().getOut().print(pageContext.getServletContext().getContextPath() + endpoint);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setEndpoint(String endpoint)
    {
        this.endpoint = endpoint;
    }
}
