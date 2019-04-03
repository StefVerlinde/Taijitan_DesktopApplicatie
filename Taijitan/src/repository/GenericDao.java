package repository;

import java.util.List;

public interface GenericDao<T> {
    List<T> findAll();
    T get(Long id);
    T update(T object);
    void delete(T object);
    void insert(T object);
    boolean exists(Long id);
}

