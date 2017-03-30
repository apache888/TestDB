package controller;

import dao.CompanyDao;
import dao.jdbc.JdbcCompanyDaoImpl;
import model.Company;

import java.util.List;

/**
 * Create by Roman Hayda on 29.03.2017.
 */
public class CompanyController implements Controller<Company> {
    private CompanyDao companyDao = new JdbcCompanyDaoImpl();

    @Override
    public void onCreate(Company company) {
        companyDao.create(company);
    }

    @Override
    public Company onGetById(int id) {
        return companyDao.getById(id);
    }

    @Override
    public List<Company> onGetAll() {
        return companyDao.getAll();
    }

    @Override
    public void onUpdate(Company company) {
        companyDao.update(company);
    }

    @Override
    public void onDelete(int id) {
        companyDao.delete(id);
    }
}
