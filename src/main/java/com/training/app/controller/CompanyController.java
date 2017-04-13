package com.training.app.controller;

import com.training.app.dao.CompanyDao;
import com.training.app.dao.hibernate.HibernateCompanyDaoImpl;
import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;
import com.training.app.model.Company;

import java.util.List;

/**
 * Create on 28.03.2017.
 * @author Roman Hayda
 *
 * Class implements interface Controller for Company object
 * class contains methods to handle CRUD events
 */
public class CompanyController implements Controller<Company> {
//    private CompanyDao companyDao = new JdbcCompanyDaoImpl();
    private CompanyDao companyDao = new HibernateCompanyDaoImpl();

    @Override
    public void onCreate(Company company) throws NotUniqueNameException, NotUniqueIdException {
        companyDao.create(company);
    }

    @Override
    public Company onGetById(int id) throws NoSuchIdException {
        return companyDao.getById(id);
    }

    @Override
    public List<Company> onGetAll() {
        return companyDao.getAll();
    }

    @Override
    public void onUpdate(Company company) throws NotUniqueNameException, NotUniqueIdException {
        companyDao.update(company);
    }

    @Override
    public void onDelete(int id) {
        companyDao.delete(id);
    }
}
