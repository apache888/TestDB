package model;

import model.entities.BaseObject;

import java.util.List;

/**
 * Create by Roman Hayda on 29.03.2017.
 */
public class CustomerModel implements Model {

    @Override
    public <T extends BaseObject> T getById(int id) {
        return null;
    }

    @Override
    public List<? extends BaseObject> getAll() {
        return null;
    }
}
