package controller;

import exception.NoSuchIdException;
import exception.NotUniqueIdException;
import exception.NotUniqueNameException;

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
