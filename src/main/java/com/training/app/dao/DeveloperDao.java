package com.training.app.dao;

import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;
import com.training.app.model.Developer;

import java.util.List;

/**
 * Create on 24.03.2017.
 * @author Roman Hayda
 *
 * interface describes DAO layer for entity Developer
 * contains CRUD methods
 */
public interface DeveloperDao {

    /**
     * create Developer object in database
     * @param developer - given Developer object
     * @throws NotUniqueIdException
     * @throws NotUniqueNameException
     */
    void create(Developer developer) throws NotUniqueIdException, NotUniqueNameException;

    /**
     * receive Developer object by ID from database
     * @param id - object ID in database
     * @return Developer object
     * @throws NoSuchIdException
     */
    Developer getById(int id) throws NoSuchIdException;

    /**
     * receive list of all Developer objects from database
     * @return List<Developer>
     */
    List<Developer> getAll();

    /**
     * update Developer object in database
     * @param developer - Developer object
     * @throws NotUniqueNameException
     * @throws NotUniqueIdException
     */
    void update(Developer developer) throws NotUniqueNameException, NotUniqueIdException;

    /**
     * delete Developer object from database by ID
     * @param id - object ID in database
     */
    void delete(int id);

}
