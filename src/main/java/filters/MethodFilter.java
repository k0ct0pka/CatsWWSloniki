package filters;

import configs.FileUploaderConfig;
import filters.wrappers.FileDiskRequestWrapper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.FileUploadException;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletDiskFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.*;
@Component
@WebFilter("/home")
public class MethodFilter implements Filter {
    private static List<String> methods = List.of("GET", "POST", "PUT", "DELETE");
    @Autowired
    private JakartaServletDiskFileUpload diskFileItemFactory;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        AnnotationConfigWebApplicationContext ac = (AnnotationConfigWebApplicationContext) filterConfig.getServletContext().getAttribute("applicationContext");

        ac.refresh();
        ac.register(FileUploaderConfig.class);
        ac.refresh();
        ac.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String method = req.getParameter("_method");
        if(method==null) {



            filterChain.doFilter(req, servletResponse);

        } else {
            if(methods.contains(method)) {

                String finalMethod = method;
                HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(req){
                    @Override
                    public String getMethod() {
                        return finalMethod;
                    }

                    @Override
                    public String getContentType() {
                        return req.getHeader("content-type");
                    }

                };

                filterChain.doFilter(requestWrapper, servletResponse);
            }
        }
    }

    private void check(ServletResponse servletResponse, FilterChain filterChain, HttpServletRequest req, String method) throws IOException, ServletException {

    }
}
