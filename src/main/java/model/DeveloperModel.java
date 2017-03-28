package model;

import dao.DeveloperDao;
import model.entities.Developer;

import java.util.List;

/**
 * Create by Roman Hayda on 28.03.2017.
 */
public class DeveloperModel implements Model {
    private DeveloperDao developerDao = new DeveloperDao();

    public Developer getById(int id) {
        return developerDao.getById(id);
    }

    public List<Developer> getAll() {
        return developerDao.getAll();
    }
}
