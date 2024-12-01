package services.authoriationImpls;

import dao.impls.UserDao;
import dto.UserDto;
import factories.impls.UserFactory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import services.AuthorizationService;
import java.util.*;

@Service
@RequiredArgsConstructor
@Component
public class AuthorizationServiceImpl implements AuthorizationService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserFactory userFactory;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void registerUser(HttpServletRequest req) {
        Map<String, String> parameters = prepareParameters(req);
        UserDto user = userFactory.build(parameters);
        userDao.save(user);
    }

    @Override
    public boolean loginUser(HttpServletRequest req) {
        UserDto userByEmail = userDao.findByEmail(req.getParameter("email"));
        if(bCryptPasswordEncoder.matches(req.getParameter("password"), userByEmail.getPassword())) {
            req.getSession().setAttribute("user", userByEmail);
            return true;
        }
        throw new RuntimeException("Invalid email or password");
    }
    private Map<String, String> prepareParameters(HttpServletRequest req) {
        Map<String,String> parameters = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(s->parameters.put(s,req.getParameter(s)));
        if(parameters.containsKey("password")){
            String encoded = bCryptPasswordEncoder.encode(parameters.get("password"));
            parameters.put("password",encoded);
        }
        return parameters;
    }
}
