package dao;

import java.util.List;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
public interface EntityDao<T> {
    void create();
    T getById(int id);
    void update(int id);
    void delete(int id);
    List<T> getAll();
}
