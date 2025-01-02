package servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import services.dtoServices.CatService;

import java.io.IOException;
@WebServlet("/delete")
@NoArgsConstructor
@Component
@ComponentScan(basePackageClasses = CatService.class)
public class DeleteCatServlet extends HttpServlet {
    @Autowired
    private CatService catService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        AnnotationConfigWebApplicationContext ac = (AnnotationConfigWebApplicationContext) config.getServletContext().getAttribute("applicationContext");

        ac.refresh();
        ac.getAutowireCapableBeanFactory().autowireBean(this);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        catService.delete(Integer.valueOf(req.getParameter("id")));
    }
}
