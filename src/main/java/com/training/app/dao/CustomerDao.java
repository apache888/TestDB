package com.training.app.dao;

import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;
import com.training.app.model.Customer;

import java.util.List;

/**
 * Create on 24.03.2017.
 * @author Roman Hayda
 *
 * interface describes DAO layer for entity Customer
 * contains CRUD methods
 */
public interface CustomerDao {

    /**
     * create Customer object in database
     * @param customer - given Customer object
     * @throws NotUniqueIdException
     * @throws NotUniqueNameException
     */
    void create(Customer customer) throws NotUniqueIdException, NotUniqueNameException;

    /**
     * receive Customer object by ID from database
     * @param id - object ID in database
     * @return Customer object
     * @throws NoSuchIdException
     */
    Customer getById(int id) throws NoSuchIdException;

    /**
     * receive list of all Customer objects from database
     * @return List<Customer>
     */
    List<Customer> getAll();

    /**
     * update Customer object in database
     * @param customer - Customer object
     * @throws NotUniqueNameException
     * @throws NotUniqueIdException
     */
    void update(Customer customer) throws NotUniqueNameException, NotUniqueIdException;

    /**
     * delete Customer object from database by ID
     * @param id - object ID in database
     */
    void delete(int id);
}
