package dao.jdbc;

import dao.CompanyDao;
import model.Company;
import model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Create by Roman Hayda on 29.03.2017.
 */
public class JdbcCompanyDaoImpl implements CompanyDao {
    private static final String URL = "jdbc:mysql://localhost:3306/it_test_db?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final String SELECT_BY_ID = "SELECT * FROM companies WHERE id=";
    private static final String SELECT_ALL = "SELECT * FROM companies";
    private static final String DELETE_BY_ID = "DELETE FROM companies WHERE id=";

    private static final String SELECT_COMPANY_PROJECTS_BY_ID = "SELECT projects.* FROM projects JOIN companies_projects on project_id= projects.id and company_id =";

    public void create(Company company) {

    }

    public Company getById(int id) {
        Company company = null;
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_BY_ID + id)){

            while (resultSet.next()) {
                int comp_id = resultSet.getInt("id");
                String name = resultSet.getString("name_company");
                company = new Company(comp_id, name);
            }
            ResultSet rs = statement.executeQuery(SELECT_COMPANY_PROJECTS_BY_ID + id);
            Set<Project> set = new HashSet<>();
            while (rs.next()) {
                int proj_id = rs.getInt("id");
                String name = rs.getString("name_project");
                set.add(new Project(proj_id, name));
            }
            rs.close();
            company.setCompanyProjects(set);
            return company;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return company;
    }

    public List<Company> getAll() {
        List<Company> list = new ArrayList<>();

        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL)){

            while (resultSet.next()) {
                int comp_id = resultSet.getInt("id");
                String name = resultSet.getString("name_company");
                Company company = new Company(comp_id, name);

                ResultSet rs = stmt.executeQuery(SELECT_COMPANY_PROJECTS_BY_ID + comp_id);
                Set<Project> set = new HashSet<>();
                while (rs.next()) {
                    int proj_id = rs.getInt("id");
                    String name_dev = rs.getString("name_project");
                    set.add(new Project(proj_id, name_dev));
                }
                rs.close();
                company.setCompanyProjects(set);
                list.add(company);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(Company company) {

    }

    public void delete(int id) {
        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement()){
            statement.executeUpdate(DELETE_BY_ID + id);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
