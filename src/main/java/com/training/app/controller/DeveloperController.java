package com.training.app.controller;

import com.training.app.dao.DeveloperDao;
import com.training.app.dao.hibernate.HibernateDeveloperDaoImpl;
import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;
import com.training.app.model.Developer;

import java.util.List;

/**
 * Create on 28.03.2017.
 * @author Roman Hayda
 *
 * Class implements interface Controller for Developer object
 * class contains methods to handle CRUD events
 */
public class DeveloperController implements Controller<Developer> {
//    private DeveloperDao developerDao = new JdbcDeveloperDaoImpl();
    private DeveloperDao developerDao = new HibernateDeveloperDaoImpl();

    @Override
    public void onCreate(Developer developer) throws NotUniqueNameException, NotUniqueIdException {
        developerDao.create(developer);
    }

    @Override
    public Developer onGetById(int id) throws NoSuchIdException {
        return developerDao.getById(id);
    }

    @Override
    public List<Developer> onGetAll() {
        return developerDao.getAll();
    }

    @Override
    public void onUpdate(Developer developer) throws NotUniqueNameException, NotUniqueIdException {
        developerDao.update(developer);
    }

    @Override
    public void onDelete(int id) {
        developerDao.delete(id);
    }
}
