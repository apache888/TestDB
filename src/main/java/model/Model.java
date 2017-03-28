package model;

import model.entities.BaseObject;

import java.util.List;

/**
 * Create by Roman Hayda on 28.03.2017.
 */
public interface Model {
    <T extends BaseObject> T getById(int id);

    List<? extends BaseObject> getAll();

}
