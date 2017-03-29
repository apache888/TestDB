package model;

import dao.SkillDao;
import model.entities.Skill;

import java.util.List;

/**
 * Create by Roman Hayda on 28.03.2017.
 */
public class SkillModel implements Model {
    private SkillDao skillDao = new SkillDao();

    @Override
    public void create() {
        skillDao.create();
    }

    public Skill getById(int id) {
        return skillDao.getById(id);
    }

    public List<Skill> getAll() {
        return skillDao.getAll();
    }

    @Override
    public void update(int id) {
        skillDao.update(id);
    }

    @Override
    public void delete(int id) {
        skillDao.delete(id);
    }
}
