package services;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthorizationService {
    void registerUser(HttpServletRequest req);
    boolean loginUser(HttpServletRequest req);
}
