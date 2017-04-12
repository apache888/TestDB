package com.training.app.controller;

import com.training.app.dao.SkillDao;
import com.training.app.dao.hibernate.HibernateSkillDaoImpl;
import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;
import com.training.app.model.Skill;

import java.util.List;

/**
 * Create by Roman Hayda on 28.03.2017.
 */
public class SkillController implements Controller<Skill> {
//    private SkillDao skillDao = new JdbcSkillDaoImpl();
    private SkillDao skillDao = new HibernateSkillDaoImpl();

    @Override
    public void onCreate(Skill skill) throws NotUniqueNameException, NotUniqueIdException {
        skillDao.create(skill);
    }

    @Override
    public Skill onGetById(int id) throws NoSuchIdException {
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
