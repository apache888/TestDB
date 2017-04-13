package com.training.app.dao;

import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;
import com.training.app.model.Project;

import java.util.List;

/**
 * Create on 24.03.2017.
 * @author Roman Hayda
 *
 * interface describes DAO layer for entity Project
 * contains CRUD methods
 */
public interface ProjectDao {

    /**
     * create Project object in database
     * @param project - given Project object
     * @throws NotUniqueIdException
     * @throws NotUniqueNameException
     */
    void create(Project project) throws NotUniqueIdException, NotUniqueNameException;

    /**
     * receive Project object by ID from database
     * @param id - object ID in database
     * @return Project object
     * @throws NoSuchIdException
     */
    Project getById(int id) throws NoSuchIdException;

    /**
     * receive list of all Project objects from database
     * @return List<Project>
     */
    List<Project> getAll();

    /**
     * update Project object in database
     * @param project - Project object
     * @throws NotUniqueNameException
     * @throws NotUniqueIdException
     */
    void update(Project project) throws NotUniqueNameException, NotUniqueIdException;

    /**
     * delete Project object from database by ID
     * @param id - object ID in database
     */
    void delete(int id);
}
