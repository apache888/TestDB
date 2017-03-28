package dao;

import dao.jdbc.JdbcDeveloperDaoImpl;
import model.entities.Developer;

import java.util.List;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
public class DeveloperDao implements EntityDao<Developer> {
    private JdbcDeveloperDaoImpl jdbcDeveloperDao = new JdbcDeveloperDaoImpl();

    public void create() {
        jdbcDeveloperDao.create();
    }

    public Developer getById(int id) {
        return jdbcDeveloperDao.getById(id);
    }

    public List<Developer> getAll() {
        return jdbcDeveloperDao.getAll();
    }

    public void update(int id) {
        jdbcDeveloperDao.update(id);
    }

    public void delete(int id) {
        jdbcDeveloperDao.delete(id);
    }

}
