package dao;

import exception.NoSuchIdException;
import exception.NotUniqueIdException;
import exception.NotUniqueNameException;
import model.Project;

import java.util.List;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
public interface ProjectDao {

    void create(Project project) throws NotUniqueIdException, NotUniqueNameException;

    Project getById(int id) throws NoSuchIdException;

    List<Project> getAll();

    void update(Project project) throws NotUniqueNameException, NotUniqueIdException;

    void delete(int id);
}
