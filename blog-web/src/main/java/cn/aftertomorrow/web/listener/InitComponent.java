package cn.aftertomorrow.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 服务启动监听器
 *
 * @author huangming
 * @date 2019/09/26
 */
public class InitComponent implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(InitComponent.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("InitComponent is running");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
