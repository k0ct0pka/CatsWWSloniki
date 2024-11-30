package dao;

public interface BaseDao<T> {
    void save(T t);
    void update(T t);
    void delete(T t);
}
