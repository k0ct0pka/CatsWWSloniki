//package listeners;
//
//import jakarta.servlet.ServletContextEvent;
//import jakarta.servlet.ServletContextListener;
//import jakarta.servlet.annotation.WebListener;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.context.support.GenericApplicationContext;
//
//@WebListener
//public class SpringApplicationContextListener implements ServletContextListener {
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        ApplicationContext ac = new GenericApplicationContext();
//        sce.getServletContext().setAttribute("applicationContext", ac);
//    }
//}
