package com.training.app.dao;

import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;
import com.training.app.model.Company;

import java.util.List;

/**
 * Create on 24.03.2017.
 * @author Roman Hayda
 *
 * interface describes DAO layer for entity Company
 * contains CRUD methods
 */
public interface CompanyDao {

    /**
     * create Company object in database
     * @param company - given Company object
     * @throws NotUniqueIdException
     * @throws NotUniqueNameException
     */
    void create(Company company) throws NotUniqueIdException, NotUniqueNameException;

    /**
     * receive Company object by ID from database
     * @param id - object ID in database
     * @return Company object
     * @throws NoSuchIdException
     */
    Company getById(int id) throws NoSuchIdException;

    /**
     * receive list of all Company objects from database
     * @return List<Company>
     */
    List<Company> getAll();

    /**
     * update Company object in database
     * @param company - Company object
     * @throws NotUniqueNameException
     * @throws NotUniqueIdException
     */
    void update(Company company) throws NotUniqueNameException, NotUniqueIdException;

    /**
     * delete Company object from database by ID
     * @param id - object ID in database
     */
    void delete(int id);
}
