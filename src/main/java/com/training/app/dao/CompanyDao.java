package com.training.app.dao;

import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;
import com.training.app.model.Company;

import java.util.List;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
//DAO layer for object Company
public interface CompanyDao {

    //create object in database
    void create(Company company) throws NotUniqueIdException, NotUniqueNameException;

    //receive object by id from database
    Company getById(int id) throws NoSuchIdException;

    //receive list of all objects from database
    List<Company> getAll();

    //update object in database
    void update(Company company) throws NotUniqueNameException, NotUniqueIdException;

    //delete object from database
    void delete(int id);
}
