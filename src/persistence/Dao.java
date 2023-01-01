package persistence;

import java.util.List;

public interface Dao<T> {
    List<T> findAll();
    T findById(int id) ;
    T save(T object) ;
    boolean delete(T object) ;
    T update(T object) ;
}
