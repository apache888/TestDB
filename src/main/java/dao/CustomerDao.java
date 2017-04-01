package dao;

import exception.NoSuchIdException;
import exception.NotUniqueIdException;
import exception.NotUniqueNameException;
import model.Customer;

import java.util.List;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
public interface CustomerDao {

    void create(Customer customer) throws NotUniqueIdException, NotUniqueNameException;

    Customer getById(int id) throws NoSuchIdException;

    List<Customer> getAll();

    void update(Customer customer) throws NotUniqueNameException, NotUniqueIdException;

    void delete(int id);
}
