//package filters;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletRequestWrapper;
//
//import java.io.IOException;
//import java.net.http.HttpRequest;
//import java.util.*;
//
//@WebFilter("/*")
//public class MethodFilter implements Filter {
//    private static List<String> methods = List.of("GET", "POST", "PUT", "DELETE");
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        String method = req.getParameter("_method");
//        if(method==null && req.getPart("_method") == null) {
//            filterChain.doFilter(req, servletResponse);
//        } else if(methods.contains(method)) {
//            HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(req){
//                @Override
//                public String getMethod() {
//                    return method;
//                }
//
//                @Override
//                public String getContentType() {
//                    return req.getHeader("content-type");
//                }
//            };
//            filterChain.doFilter(requestWrapper, servletResponse);
//        }
//    }
//}
