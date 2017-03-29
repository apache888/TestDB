package model;

import dao.DeveloperDao;
import model.entities.Developer;

import java.util.List;

/**
 * Create by Roman Hayda on 28.03.2017.
 */
public class DeveloperModel implements Model {
    private DeveloperDao developerDao = new DeveloperDao();

    @Override
    public void create() {
        developerDao.create();
    }

    @Override
    public Developer getById(int id) {
        return developerDao.getById(id);
    }

    @Override
    public List<Developer> getAll() {
        return developerDao.getAll();
    }

    @Override
    public void update(int id) {
        developerDao.update(id);
    }

    @Override
    public void delete(int id) {
        developerDao.delete(id);
    }
}
