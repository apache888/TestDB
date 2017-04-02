package dao;

import exception.NoSuchIdException;
import exception.NotUniqueIdException;
import exception.NotUniqueNameException;
import model.Project;

import java.util.List;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
//DAO layer for object Project
public interface ProjectDao {

    //create object in database
    void create(Project project) throws NotUniqueIdException, NotUniqueNameException;

    //receive object by id from database
    Project getById(int id) throws NoSuchIdException;

    //receive list of all objects from database
    List<Project> getAll();

    //update object in database
    void update(Project project) throws NotUniqueNameException, NotUniqueIdException;

    //delete object from database
    void delete(int id);
}
