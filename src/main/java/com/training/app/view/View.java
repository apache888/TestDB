package com.training.app.view;

import com.training.app.controller.Controller;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;

/**
 * Create by Roman Hayda on 28.03.2017.
 */
//View in MVC pattern
public interface View {

    //set org.training.app.controller for org.training.app.view (MVC)
    void setController(Controller controller);

    //fire event to create entity in database
    void fireEventCreate() throws NotUniqueNameException, NotUniqueIdException;

    //fire event to receive entity by id from database
    void fireEventGetById();

    //fire event to receive list of all entities from database
    void fireEventGetAll();

    //fire event to update entity in database
    void fireEventUpdate() throws NotUniqueNameException, NotUniqueIdException;

    //fire event to delete entity from database
    void fireEventDelete();
}