package controller;

import dao.CustomerDao;
import dao.jdbc.JdbcCustomerDaoImpl;
import exception.NoSuchIdException;
import exception.NotUniqueIdException;
import exception.NotUniqueNameException;
import model.Customer;

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
