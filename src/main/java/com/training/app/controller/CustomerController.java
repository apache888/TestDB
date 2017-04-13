package com.training.app.controller;

import com.training.app.dao.CustomerDao;
import com.training.app.dao.hibernate.HibernateCustomerDaoImpl;
import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;
import com.training.app.model.Customer;

import java.util.List;

/**
 * Create on 28.03.2017.
 * @author Roman Hayda
 *
 * Class implements interface Controller for Customer object
 * class contains methods to handle CRUD events
 */
public class CustomerController implements Controller<Customer> {
//    private CustomerDao customerDao = new JdbcCustomerDaoImpl();
    private CustomerDao customerDao = new HibernateCustomerDaoImpl();

    @Override
    public void onCreate(Customer customer) throws NotUniqueNameException, NotUniqueIdException {
        customerDao.create(customer);
    }

    @Override
    public Customer onGetById(int id) throws NoSuchIdException {
        return customerDao.getById(id);
    }

    @Override
    public List<Customer> onGetAll() {
        return customerDao.getAll();
    }

    @Override
    public void onUpdate(Customer customer) throws NotUniqueNameException, NotUniqueIdException {
        customerDao.update(customer);
    }

    @Override
    public void onDelete(int id) {
        customerDao.delete(id);
    }
}
