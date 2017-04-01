package dao;

import exception.NoSuchIdException;
import exception.NotUniqueIdException;
import exception.NotUniqueNameException;
import model.Company;

import java.util.List;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
public interface CompanyDao {

    void create(Company company) throws NotUniqueIdException, NotUniqueNameException;

    Company getById(int id) throws NoSuchIdException;

    List<Company> getAll();

    void update(Company company) throws NotUniqueNameException, NotUniqueIdException;

    void delete(int id);
}
