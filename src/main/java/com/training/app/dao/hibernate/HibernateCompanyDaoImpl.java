package com.training.app.dao.hibernate;

import com.training.app.dao.CompanyDao;
import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;
import com.training.app.model.Company;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Create on 08.04.2017.
 * @author Roman Hayda
 *
 * Class implements DAO layer for entity Company
 * contains CRUD methods
 */
public class HibernateCompanyDaoImpl implements CompanyDao {

    @Override
    public void create(Company company) throws NotUniqueIdException, NotUniqueNameException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            if (existWithName(session, company.getName())) {
                session.getTransaction().rollback();
                throw new NotUniqueNameException("Company name \'" + company.getName() + "\' not unique");
            }
        } catch (NoResultException e) {
            //NOP
        }
        session.save(company);
        session.flush();
        session.getTransaction().commit();
    }

    @Override
    public Company getById(int id) throws NoSuchIdException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Company company = null;
        try {
            company = (Company) session.createQuery("from Company C join fetch C.companyProjects where C.id= :id").setParameter("id", id).getSingleResult();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw new NoSuchIdException("No such ID");
        }
        return company;
    }

    @Override
    public List<Company> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Company> companies = (List<Company>) session.createQuery("from Company C join fetch C.companyProjects").getResultList();
        session.getTransaction().commit();
        return companies;
    }

    @Override
    public void update(Company company) throws NotUniqueNameException, NotUniqueIdException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            Company companyFromDB = (Company) session.createQuery("from Company C join fetch C.companyProjects where C.id= :id").setParameter("id", company.getId()).getSingleResult();
            companyFromDB.setCompanyProjects(company.getCompanyProjects());
            session.flush();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            create(company);
        }
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            Company company = session.get(Company.class, id);
            session.delete(company);
            session.flush();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw new IllegalArgumentException("No such ID");
        }
    }

    /**
     * Method checks if exist Company object with same name in database
     * @param session
     * @param name
     * @return
     * @throws NoResultException
     */
    private boolean existWithName(Session session, String name) throws NoResultException {
        return session.createQuery("select C.name from Company as C where C.name =\'" + name + "\'").getSingleResult() != null;
    }
}
