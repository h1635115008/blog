package cn.aftertomorrow.util;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

public class ServletContextUtil {
    public static ApplicationContext getApplicationContext() {
        ApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
        return applicationContext;
    }

    public static ServletContext getServletContext() {
        return ((WebApplicationContext) getApplicationContext()).getServletContext();
    }
}
