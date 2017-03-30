package controller;

import dao.CustomerDao;
import dao.jdbc.JdbcCustomerDaoImpl;
import model.Customer;

import java.util.List;

/**
 * Create by Roman Hayda on 29.03.2017.
 */
public class CustomerController implements Controller<Customer> {
    private CustomerDao customerDao = new JdbcCustomerDaoImpl();

    @Override
    public void onCreate(Customer customer) {
        customerDao.create(customer);
    }

    @Override
    public Customer onGetById(int id) {
        return customerDao.getById(id);
    }

    @Override
    public List<Customer> onGetAll() {
        return customerDao.getAll();
    }

    @Override
    public void onUpdate(Customer customer) {
        customerDao.update(customer);
    }

    @Override
    public void onDelete(int id) {
        customerDao.delete(id);
    }
}
