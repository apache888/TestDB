package dao;

import model.Developer;

import java.util.List;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
public interface DeveloperDao {

    void create(Developer developer);

    Developer getById(int id);

    List<Developer> getAll();

    void update(Developer developer);

    void delete(int id);

}
