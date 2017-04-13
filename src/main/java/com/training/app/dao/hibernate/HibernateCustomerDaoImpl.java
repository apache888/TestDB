package com.training.app.dao.hibernate;

import com.training.app.dao.CustomerDao;
import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;
import com.training.app.model.Customer;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Create on 08.04.2017.
 * @author Roman Hayda
 *
 * Class implements DAO layer for entity Customer
 * contains CRUD methods
 */
public class HibernateCustomerDaoImpl implements CustomerDao {

    @Override
    public void create(Customer customer) throws NotUniqueIdException, NotUniqueNameException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            if (existWithName(session, customer.getName())) {
                session.getTransaction().rollback();
                throw new NotUniqueNameException("Customer name \'" + customer.getName() + "\' not unique");
            }
        } catch (NoResultException e) {
            //NOP
        }
        session.save(customer);
        session.flush();
        session.getTransaction().commit();
    }

    @Override
    public Customer getById(int id) throws NoSuchIdException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Customer customer = null;
        try {
            customer = (Customer) session.createQuery("from Customer C join fetch C.customerProjects where C.id= :id").setParameter("id", id).getSingleResult();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw new NoSuchIdException("No such ID");
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Customer> customers = (List<Customer>) session.createQuery("from Customer C join fetch C.customerProjects").getResultList();
        session.getTransaction().commit();
        return customers;
    }

    @Override
    public void update(Customer customer) throws NotUniqueNameException, NotUniqueIdException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            Customer customerFromDB = (Customer) session.createQuery("from Customer C join fetch C.customerProjects where C.id= :id").setParameter("id", customer.getId()).getSingleResult();
            customerFromDB.setCustomerProjects(customer.getCustomerProjects());
            session.flush();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            create(customer);
        }
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            Customer customer = session.get(Customer.class, id);
            session.delete(customer);
            session.flush();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw new IllegalArgumentException("No such ID");
        }
    }

    /**
     * Method checks if exist Customer object with same name in database
     * @param session
     * @param name
     * @return
     * @throws NoResultException
     */
    private boolean existWithName(Session session, String name) throws NoResultException {
        return session.createQuery("select C.name from Customer as C where C.name =\'" + name + "\'").getSingleResult() != null;
    }
}
