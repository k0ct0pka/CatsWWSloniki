package services;


import jakarta.servlet.http.HttpServletRequest;

import java.util.*;

public interface BaseDtoService<T> {
    List<T> getAll();
    void delete(Integer id);
    void update(HttpServletRequest req);
    void save(HttpServletRequest req);
}
