package com.training.app.controller;

import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;

import java.util.List;

/**
 * Create on 23.03.2017.
 * @author Roman Hayda
 *
 * Controller implementation in MVC pattern
 * interface contains methods to handle CRUD events
 * generic by entity type
 */
public interface Controller<T> {

    /**
     * handles event to create entity in database
     * @param obj - Entity object
     * @throws NotUniqueNameException
     * @throws NotUniqueIdException
     */
    void onCreate(T obj) throws NotUniqueNameException, NotUniqueIdException;

    /**
     * handles event to receive entity by id from database
     * @param id - Entity id
     * @return
     * @throws NoSuchIdException
     */
    T onGetById(int id) throws NoSuchIdException;

    /**
     * handles event to receive list of all Entity objects from database
     * @return List<T>
     */
    List<T> onGetAll();

    /**
     * handles event to update entity in database
     * @param obj - Entity object
     * @throws NotUniqueNameException
     * @throws NotUniqueIdException
     */
    void onUpdate(T obj) throws NotUniqueNameException, NotUniqueIdException;

    /**
     * handles event to delete entity from database
     * @param id - Entity id
     */
    void onDelete(int id);
}
