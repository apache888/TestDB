package com.training.app.dao.hibernate;

import com.training.app.dao.ProjectDao;
import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;
import com.training.app.model.Project;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Create by Roman Hayda on 08.04.2017.
 */
public class HibernateProjectDaoImpl implements ProjectDao {

    @Override
    public void create(Project project) throws NotUniqueIdException, NotUniqueNameException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
        /*if (existWithId(session, skill.getId())) {
            throw new NotUniqueIdException("ID " + skill.getId() + " not unique.");
        } else*/
            if (existWithName(session, project.getName())) {
                throw new NotUniqueNameException("Specialty \'" + project.getName() + "\' not unique");
            }
        } catch (NoResultException e) {
            //NOP
        }
        session.save(project);
        session.flush();
        session.getTransaction().commit();
    }

    @Override
    public Project getById(int id) throws NoSuchIdException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Project project = (Project) session.createQuery("from Project P join fetch P.projectDevelopers where P.id= :id").setParameter("id", id).getSingleResult();
        session.getTransaction().commit();
        return project;
    }

    @Override
    public List<Project> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Project> projects = (List<Project>) session.createQuery("from Project P join fetch P.projectDevelopers").getResultList();
        session.getTransaction().commit();
        return projects;
    }

    @Override
    public void update(Project project) throws NotUniqueNameException, NotUniqueIdException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        /*if (existWithId(session, project.getId())) {
            throw new NotUniqueIdException("ID " + project.getId() + " not unique.");
        }*/
        session.saveOrUpdate(project);
        session.flush();
        session.getTransaction().commit();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Project project = session.get(Project.class, id);
        session.delete(project);
        session.flush();
        session.getTransaction().commit();
    }

    private boolean existWithName(Session session, String name) throws NoResultException {
        return session.createQuery("select P.name from Project as P where P.name =\'" + name + "\'").getSingleResult() != null;
    }

    private boolean existWithId(Session session, int id) throws NoResultException {
        return session.createQuery("select P.id from Project as P where P.id =" + id).getSingleResult() != null;
    }
}
