package com.training.app.dao.hibernate;

import com.training.app.dao.SkillDao;
import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;
import com.training.app.model.Skill;
import org.hibernate.Criteria;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Create by Roman Hayda on 08.04.2017.
 */
public class HibernateSkillDaoImpl implements SkillDao {

    @Override
    public void create(Skill skill) throws NotUniqueIdException, NotUniqueNameException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
        /*if (existWithId(session, skill.getId())) {
            throw new NotUniqueIdException("ID " + skill.getId() + " not unique.");
        } else*/
            if (existWithName(session, skill.getSpecialty())) {
                throw new NotUniqueNameException("Specialty \'" + skill.getSpecialty() + "\' not unique");
            }
        } catch (NoResultException e) {
            //NOP
        }
        session.save(skill);
        session.flush();
        session.getTransaction().commit();
    }

    @Override
    public Skill getById(int id) throws NoSuchIdException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Skill skill = session.get(Skill.class, id);
        session.getTransaction().commit();
        return skill;
    }

    @Override
    public List<Skill> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Skill.class);
        List<Skill> skills = criteria.list();
        session.getTransaction().commit();
        return skills;
    }

    @Override
    public void update(Skill skill) throws NotUniqueNameException, NotUniqueIdException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        /*if (existWithId(session, skill.getId())) {
            throw new NotUniqueIdException("ID " + skill.getId() + " not unique.");
        }*/
        session.saveOrUpdate(skill);
//        session.update(skill);
        session.getTransaction().commit();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Skill skill = session.get(Skill.class, id);
        session.delete(skill);
        session.getTransaction().commit();
    }

    private boolean existWithName(Session session, String name) throws NoResultException {

        return session.createQuery("select S.specialty from Skill as S where specialty =\'" + name + "\'").getSingleResult() != null;
    }

    private boolean existWithId(Session session, int id) throws NoResultException {
        return session.createQuery("select S.id from Skill as S where id =" + id).getSingleResult() != null;
    }
}
