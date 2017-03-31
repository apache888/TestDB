package dao;

import exception.NotUniqueIdException;
import exception.NotUniqueNameException;
import model.Developer;

import java.util.List;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
public interface DeveloperDao {

    void create(Developer developer) throws NotUniqueIdException, NotUniqueNameException;

    Developer getById(int id);

    List<Developer> getAll();

    void update(Developer developer) throws NotUniqueNameException, NotUniqueIdException;

    void delete(int id);

}
