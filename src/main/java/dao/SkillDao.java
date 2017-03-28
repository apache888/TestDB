package dao;

import dao.jdbc.JdbcSkillDaoImpl;
import model.entities.Skill;

import java.util.List;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
public class SkillDao implements EntityDao<Skill> {
    private JdbcSkillDaoImpl jdbcSkillDao = new JdbcSkillDaoImpl();

    public void create() {
        jdbcSkillDao.create();
    }

    public Skill getById(int id) {
        return jdbcSkillDao.getById(id);
    }

    public List<Skill> getAll() {
        return jdbcSkillDao.getAll();
    }

    public void update(int id) {
        jdbcSkillDao.update(id);
    }

    public void delete(int id) {
        jdbcSkillDao.delete(id);
    }
}
