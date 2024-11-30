//package servlets;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
//import org.springframework.context.ApplicationContext;
//
//public abstract class AbstractServlet extends HttpServlet {
//    protected AutowireCapableBeanFactory ctx;
//    @Override
//    public void init() throws ServletException {
//        super.init();
//
//        ctx = ((ApplicationContext) getServletContext().getAttribute("applicationContext")).getAutowireCapableBeanFactory();
//        ctx.autowireBean(this);
//    }
//}
