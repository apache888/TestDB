package controller;

import dao.CompanyDao;
import dao.jdbc.JdbcCompanyDaoImpl;
import exception.NoSuchIdException;
import exception.NotUniqueIdException;
import exception.NotUniqueNameException;
import model.Company;

import java.util.List;

/**
 * Create by Roman Hayda on 29.03.2017.
 */
public class CompanyController implements Controller<Company> {
    private CompanyDao companyDao = new JdbcCompanyDaoImpl();

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
