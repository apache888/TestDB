package dao;

import model.Customer;

import java.util.List;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
public interface CustomerDao {

    void create(Customer customer);

    Customer getById(int id);

    List<Customer> getAll();

    void update(Customer customer);

    void delete(int id);
}
