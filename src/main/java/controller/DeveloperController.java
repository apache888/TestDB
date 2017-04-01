package controller;

import dao.DeveloperDao;
import dao.jdbc.JdbcDeveloperDaoImpl;
import exception.NoSuchIdException;
import exception.NotUniqueIdException;
import exception.NotUniqueNameException;
import model.Developer;

import java.util.List;

/**
 * Create by Roman Hayda on 28.03.2017.
 */
public class DeveloperController implements Controller<Developer> {
    private DeveloperDao developerDao = new JdbcDeveloperDaoImpl();

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
