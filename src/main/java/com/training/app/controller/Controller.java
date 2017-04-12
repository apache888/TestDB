package com.training.app.controller;

import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;

import java.util.List;

/**
 * Create by Roman Hayda on 28.03.2017.
 */
public interface Controller<T> {

    void onCreate(T obj) throws NotUniqueNameException, NotUniqueIdException;

    T onGetById(int id) throws NoSuchIdException;

    List<T> onGetAll();

    void onUpdate(T obj) throws NotUniqueNameException, NotUniqueIdException;

    void onDelete(int id);
}
