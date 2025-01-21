package servlets;

import configs.HikariConfiguration;
import dao.impls.CatDao;
import dao.impls.UserDao;
import dto.CatDto;
import factories.impls.CatFactory;
import factories.impls.UserFactory;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import services.authoriationImpls.AuthorizationServiceImpl;
import services.dtoServices.CatService;
import services.storageImpls.S3StorageService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/home")
@NoArgsConstructor
@Component
@ComponentScan(basePackageClasses = CatService.class)
@MultipartConfig(
        fileSizeThreshold = 1024*1024*2,
        maxFileSize = 1024*1024*10,
        maxRequestSize = 1024*1024*11
)
public class HomeServlet extends HttpServlet {
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CatDto> all = catService.getAll();
        Collections.shuffle(all);
        req.getSession().setAttribute("cats",all);
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        catService.save(req);
        resp.sendRedirect("/home");
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        catService.update(req);
        resp.sendRedirect("/home");
    }

}
