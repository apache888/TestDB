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
 * Create on 08.04.2017.
 * @author Roman Hayda
 *
 * Class implements DAO layer for entity Project
 * contains CRUD methods
 */
public class HibernateProjectDaoImpl implements ProjectDao {

    @Override
    public void create(Project project) throws NotUniqueIdException, NotUniqueNameException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            if (existWithName(session, project.getName())) {
                session.getTransaction().rollback();
                throw new NotUniqueNameException("Project name \'" + project.getName() + "\' not unique");
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
        Project project = null;
        try {
            project = (Project) session.createQuery("from Project P join fetch P.projectDevelopers where P.id= :id").setParameter("id", id).getSingleResult();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw new NoSuchIdException("No such ID");
        }
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
        try {
            Project projectFromDB = getById(project.getId());
            projectFromDB.setCost(project.getCost());
            projectFromDB.setProjectDevelopers(project.getProjectDevelopers());
            session.update(projectFromDB);
            session.flush();
            session.getTransaction().commit();
        } catch (NoSuchIdException e) {
            session.getTransaction().rollback();
            create(project);
        }
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            Project project = session.get(Project.class, id);
            session.delete(project);
            session.flush();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw new IllegalArgumentException("No such ID");
        }
    }

    /**
     * Method checks if exist Project object with same name in database
     * @param session
     * @param name
     * @return
     * @throws NoResultException
     */
    private boolean existWithName(Session session, String name) throws NoResultException {
        return session.createQuery("select P.name from Project as P where P.name =\'" + name + "\'").getSingleResult() != null;
    }
}
