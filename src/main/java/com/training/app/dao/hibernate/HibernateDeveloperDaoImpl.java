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
 * Create on 08.04.2017.
 * @author Roman Hayda
 *
 * Class implements DAO layer for entity Developer
 * contains CRUD methods
 */
public class HibernateDeveloperDaoImpl implements DeveloperDao {

    @Override
    public void create(Developer developer) throws NotUniqueIdException, NotUniqueNameException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        /*try {
            if (existWithName(session, developer.getName())) {
                session.getTransaction().rollback();
                throw new NotUniqueNameException("Developer name \'" + developer.getName() + "\' not unique");
            }
        } catch (NoResultException e) {
            //NOP
        }*/
        session.save(developer);
        session.flush();
        session.getTransaction().commit();
    }

    @Override
    public Developer getById(int id) throws NoSuchIdException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Developer developer = null;
        try {
            developer = (Developer) session.createQuery("from Developer D join fetch D.skills where D.id= :id").setParameter("id", id).getSingleResult();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw new NoSuchIdException("No such ID");
        }
        return developer;
    }

    @Override
    public List<Developer> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Developer> developers = (List<Developer>) session.createQuery("from Developer D join fetch D.skills").getResultList();
        session.getTransaction().commit();
        return developers;
    }

    @Override
    public void update(Developer developer) throws NotUniqueNameException, NotUniqueIdException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            Developer devFromDB = getById(developer.getId());
            devFromDB.setExperience(developer.getExperience());
            devFromDB.setSalary(developer.getSalary());
            devFromDB.setSkills(developer.getSkills());
            session.update(devFromDB);
            session.flush();
            session.getTransaction().commit();
        } catch (NoSuchIdException e) {
            session.getTransaction().rollback();
            create(developer);
        }
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            Developer developer = session.get(Developer.class, id);
            session.delete(developer);
            session.flush();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw new IllegalArgumentException("No such ID");
        }
    }
    /**
     * Method checks if exist Developer object with same name in database
     * @param session
     * @param name
     * @return
     * @throws NoResultException
     */
    private boolean existWithName(Session session, String name) throws NoResultException {

        return session.createQuery("select D.name from Developer as D where D.name =\'" + name + "\'").getSingleResult() != null;
    }
}
