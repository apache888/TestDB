package com.training.app.controller;

import com.training.app.dao.CustomerDao;
import com.training.app.dao.jdbc.JdbcCustomerDaoImpl;
import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;
import com.training.app.model.Customer;

import java.util.List;

/**
 * Create by Roman Hayda on 29.03.2017.
 */
public class CustomerController implements Controller<Customer> {
    private CustomerDao customerDao = new JdbcCustomerDaoImpl();

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
