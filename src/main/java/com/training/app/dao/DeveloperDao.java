package com.training.app.dao;

import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;
import com.training.app.model.Developer;

import java.util.List;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
//DAO layer for object Developer
public interface DeveloperDao {

    //create object in database
    void create(Developer developer) throws NotUniqueIdException, NotUniqueNameException;

    //receive object by id from database
    Developer getById(int id) throws NoSuchIdException;

    //receive list of all objects from database
    List<Developer> getAll();

    //update object in database
    void update(Developer developer) throws NotUniqueNameException, NotUniqueIdException;

    //delete object from database
    void delete(int id);

}
