package dao;

import exception.NoSuchIdException;
import exception.NotUniqueIdException;
import exception.NotUniqueNameException;
import model.Customer;

import java.util.List;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
//DAO layer for object Customer
public interface CustomerDao {

    //create object in database
    void create(Customer customer) throws NotUniqueIdException, NotUniqueNameException;

    //receive object by id from database
    Customer getById(int id) throws NoSuchIdException;

    //receive list of all objects from database
    List<Customer> getAll();

    //update object in database
    void update(Customer customer) throws NotUniqueNameException, NotUniqueIdException;

    //delete object from database
    void delete(int id);
}
