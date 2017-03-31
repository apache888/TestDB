package controller;

import dao.SkillDao;
import dao.jdbc.JdbcSkillDaoImpl;
import exception.NotUniqueIdException;
import exception.NotUniqueNameException;
import model.Skill;

import java.util.List;

/**
 * Create by Roman Hayda on 28.03.2017.
 */
public class SkillController implements Controller<Skill> {
    private SkillDao skillDao = new JdbcSkillDaoImpl();

    @Override
    public void onCreate(Skill skill) throws NotUniqueNameException, NotUniqueIdException {
        skillDao.create(skill);
    }

    @Override
    public Skill onGetById(int id) {
       return skillDao.getById(id);
    }

    @Override
    public List<Skill> onGetAll() {
        return skillDao.getAll();
    }

    @Override
    public void onUpdate(Skill skill) throws NotUniqueNameException, NotUniqueIdException {
        skillDao.update(skill);
    }

    @Override
    public void onDelete(int id) {
        skillDao.delete(id);
    }
}
