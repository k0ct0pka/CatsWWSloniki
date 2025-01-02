package dao;

public interface BaseDao<T> {
    Integer save(T t);
    void update(T t);
    void delete(Integer id);
}
