package ru.itis.listeners;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.config.SpringConfig;
import ru.itis.config.persistence.PersistenceConfig;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



public class SpringContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        sce.getServletContext().setAttribute("springContext", context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}