package com.training.app.dao.hibernate;

import com.training.app.dao.DeveloperDao;
import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;
import com.training.app.model.Developer;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Create by Roman Hayda on 08.04.2017.
 */
public class HibernateDeveloperDaoImpl implements DeveloperDao {

    @Override
    public void create(Developer developer) throws NotUniqueIdException, NotUniqueNameException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
        /*if (existWithId(session, skill.getId())) {
            throw new NotUniqueIdException("ID " + skill.getId() + " not unique.");
        } else*/
            if (existWithName(session, developer.getName())) {
                throw new NotUniqueNameException("Specialty \'" + developer.getName() + "\' not unique");
            }
        } catch (NoResultException e) {
            //NOP
        }
        session.save(developer);
        session.flush();
        session.getTransaction().commit();
    }

    @Override
    public Developer getById(int id) throws NoSuchIdException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
//        Developer developer = session.get(Developer.class, id);
//        List<Skill> skills = session.createQuery("",Skill.class).list();
        Developer developer = (Developer) session.createQuery("from Developer D join fetch D.skills where D.id= :id").setParameter("id", id).getSingleResult();
        session.getTransaction().commit();
        return developer;
    }

    @Override
    public List<Developer> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
//        Criteria criteria = session.createCriteria(Developer.class);
//        List<Developer> developers = criteria.list();
        List<Developer> developers = (List<Developer>) session.createQuery("from Developer D join fetch D.skills").getResultList();
        session.getTransaction().commit();
        return developers;
    }

    @Override
    public void update(Developer developer) throws NotUniqueNameException, NotUniqueIdException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        /*if (existWithId(session, developer.getId())) {
            throw new NotUniqueIdException("ID " + developer.getId() + " not unique.");
        }*/
        session.saveOrUpdate(developer);
//        session.update(developer);
        session.flush();
        session.getTransaction().commit();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Developer developer = session.get(Developer.class, id);
        session.delete(developer);
        session.flush();
        session.getTransaction().commit();
    }

    private boolean existWithName(Session session, String name) throws NoResultException {

        return session.createQuery("select D.name from Developer as D where D.name =\'" + name + "\'").getSingleResult() != null;
    }

    private boolean existWithId(Session session, int id) throws NoResultException {
//        return session.get(Skill.class, id) != null;
        return session.createQuery("select D.id from Developer as D where D.id =" + id).getSingleResult() != null;
    }
}
