package dao;

import model.Company;

import java.util.List;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
public interface CompanyDao {

    void create(Company company);

    Company getById(int id);

    List<Company> getAll();

    void update(Company company);

    void delete(int id);
}
