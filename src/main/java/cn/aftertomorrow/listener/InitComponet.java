package cn.aftertomorrow.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitComponet implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("InitCompont is running");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
