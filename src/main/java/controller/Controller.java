package controller;

import java.util.List;

/**
 * Create by Roman Hayda on 28.03.2017.
 */
public interface Controller<T> {

    void onCreate(T obj);

    T onGetById(int id);

    List<T> onGetAll();

    void onUpdate(T obj);

    void onDelete(int id);
}
