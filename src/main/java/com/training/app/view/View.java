package com.training.app.view;

import com.training.app.controller.Controller;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;

/**
 * Create on 23.03.2017.
 * @author Roman Hayda
 *
 * View implementation in MVC pattern
 * interface contains methods to fire CRUD events
 */
public interface View {
    /**
     * set Controller for View (MVC)
     * @param controller - specific controller for particular object
     */
    void setController(Controller controller);

    /**
     * fire event to create entity in database
     * @throws NotUniqueNameException
     * @throws NotUniqueIdException
     */
    void fireEventCreate() throws NotUniqueNameException, NotUniqueIdException;

    /**
     * fire event to receive entity by id from database
     */
    void fireEventGetById();

    /**
     * fire event to receive list of all entities from database
     */
    void fireEventGetAll();

    /**
     * fire event to update entity in database
     * @throws NotUniqueNameException
     * @throws NotUniqueIdException
     */
    void fireEventUpdate() throws NotUniqueNameException, NotUniqueIdException;

    /**
     * fire event to delete entity from database
     */
    void fireEventDelete();
}
