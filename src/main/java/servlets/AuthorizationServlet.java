package servlets;

import configs.HikariConfiguration;
import dao.impls.UserDao;
import factories.impls.UserFactory;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
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
import services.AuthorizationService;
import services.authoriationImpls.AuthorizationServiceImpl;

import java.io.IOException;

@WebServlet("/authorize")
@NoArgsConstructor
@Component
@ComponentScan(basePackageClasses = AuthorizationServiceImpl.class)
public class AuthorizationServlet extends HttpServlet {
    @Autowired
    private AuthorizationServiceImpl authorizationService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        AnnotationConfigWebApplicationContext ac = (AnnotationConfigWebApplicationContext) config.getServletContext().getAttribute("applicationContext");

        ac.scan("org.example");
        ac.register(HikariConfiguration.class);
        ac.refresh();
        ac.register(UserDao.class);
        ac.register(UserFactory.class);
        ac.register(BCryptPasswordEncoder.class);
        ac.register(AuthorizationServiceImpl.class);
        ac.refresh();
        ac.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorizationService.registerUser(req);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorizationService.loginUser(req);
    }

}
