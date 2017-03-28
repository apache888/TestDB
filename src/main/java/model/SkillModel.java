package model;

import dao.SkillDao;
import model.entities.Skill;

import java.util.List;

/**
 * Create by Roman Hayda on 28.03.2017.
 */
public class SkillModel implements Model {
    private SkillDao skillDao = new SkillDao();

    public Skill getById(int id) {
        return skillDao.getById(id);
    }

    public List<Skill> getAll() {
        return skillDao.getAll();
    }
}
