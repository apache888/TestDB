package dao.jdbc;

import dao.CompanyDao;
import exception.NoSuchIdException;
import exception.NotUniqueIdException;
import exception.NotUniqueNameException;
import model.Company;
import model.Project;

import java.sql.*;
import java.util.*;

/**
 * Create by Roman Hayda on 29.03.2017.
 */
public class JdbcCompanyDaoImpl implements CompanyDao {
    private static final String URL = "jdbc:mysql://localhost:3306/it_test_db?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final String SELECT_BY_ID = "SELECT * FROM companies WHERE id=?";
    private static final String SELECT_ALL = "SELECT * FROM companies";
    private static final String SELECT_BY_NAME = "SELECT * FROM companies WHERE name_company=?";
    private static final String DELETE_BY_ID = "DELETE FROM companies WHERE id=?";
    private static final String INSERT = "INSERT INTO companies VALUES (?,?)";
    private static final String INSERT_AUTO_ID = "INSERT INTO companies (name_company) VALUES (?)";
    private static final String UPDATE_BY_ID = "UPDATE companies SET name_company=? WHERE id=?";

    private static final String SELECT_COMPANY_PROJECTS_BY_ID = "SELECT project_id, share FROM companies_projects WHERE company_id =?";
    private static final String INSERT_COMPANY_PROJECT = "INSERT INTO companies_projects VALUES (?, ?, ?)";
    private static final String SELECT_PROJECTS_ID = "SELECT project_id FROM companies_projects WHERE company_id=?";
    private static final String DELETE_PROJECTS = "DELETE FROM companies_projects WHERE company_id=? AND project_id=?";
    private static final String UPDATE_SHARE = "UPDATE companies_projects SET share=? WHERE company_id=? AND project_id=?";

    public void create(Company company) throws NotUniqueIdException, NotUniqueNameException {
        PreparedStatement prstmt;
        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD)){
            if (company.getId() == 0 && !existWithName(connection, company.getName())) {
                prstmt = connection.prepareStatement(INSERT_AUTO_ID);
                prstmt.setString(1, company.getName());
                prstmt.executeUpdate();
                createCompanyProjectsRecords(connection, company);
            } else if (existWithId(connection, company.getId())) {
                throw new NotUniqueIdException("ID " + company.getId() + " is not unique.");
            } else if (existWithName(connection, company.getName())) {
                throw new NotUniqueNameException("Company's name \'" + company.getName() + "\' is not unique");
            } else {
                prstmt = connection.prepareStatement(INSERT);
                prstmt.setInt(1, company.getId());
                prstmt.setString(2, company.getName());
                prstmt.executeUpdate();
                createCompanyProjectsRecords(connection, company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Company getById(int id) throws NoSuchIdException {
        Company company = null;
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            PreparedStatement ps = connection.prepareStatement(SELECT_COMPANY_PROJECTS_BY_ID)){

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int companyId = resultSet.getInt("id");
                String nameCompany = resultSet.getString("name_company");
                company = new Company(companyId, nameCompany);
            }
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Map<Project, Integer> projects = new HashMap<>();
            JdbcProjectDaoImpl projectDao = new JdbcProjectDaoImpl();
            while (rs.next()) {
                int projectId = rs.getInt("project_id");
                int share = rs.getInt("share");
                Project project = projectDao.getById(projectId);
                projects.put(project, share);
            }
            rs.close();
            resultSet.close();
            if (company != null) {
                company.setCompanyProjects(projects);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (company != null) {
            return company;
        } else {
            throw new NoSuchIdException("There is no record in \"companies\" with ID " + id + "\n");
        }
    }

    public List<Company> getAll() {
        List<Company> list = new ArrayList<>();

        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement(SELECT_COMPANY_PROJECTS_BY_ID);
            ResultSet resultSet = statement.executeQuery(SELECT_ALL)){

            while (resultSet.next()) {
                int companyId = resultSet.getInt("id");
                String nameCompany = resultSet.getString("name_company");
                Company company = new Company(companyId, nameCompany);

                ps.setInt(1, companyId);
                ResultSet rs = ps.executeQuery();
                Map<Project, Integer> projects = new HashMap<>();
                JdbcProjectDaoImpl projectDao = new JdbcProjectDaoImpl();
                while (rs.next()) {
                    int projectId = rs.getInt("project_id");
                    int share = rs.getInt("share");
                    Project project = projectDao.getById(projectId);
                    projects.put(project, share);
                }
                rs.close();
                company.setCompanyProjects(projects);
                list.add(company);
            }
            resultSet.close();
        } catch (SQLException | NoSuchIdException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(Company company) throws NotUniqueNameException, NotUniqueIdException {
        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            PreparedStatement prstmt = connection.prepareStatement(UPDATE_BY_ID);
            PreparedStatement pr = connection.prepareStatement(SELECT_PROJECTS_ID);
            PreparedStatement ps = connection.prepareStatement(INSERT_COMPANY_PROJECT);
            PreparedStatement psUpShare = connection.prepareStatement(UPDATE_SHARE);
            ResultSet rs = statement.executeQuery(SELECT_ALL)){

            List<Integer> idList = new ArrayList<>();
            while (rs.next()) {
                idList.add(rs.getInt("id"));
            }
            if (!idList.contains(company.getId())) {
                create(company);
            } else {
                prstmt.setString(1, company.getName());
                prstmt.setInt(2, company.getId());
                prstmt.executeUpdate();
                Map<Project, Integer> setForUpdate = company.getCompanyProjects();
                Set<Integer> idProjects = new HashSet<>();
                pr.setInt(1, company.getId());
                ResultSet resultSet = pr.executeQuery();
                while (resultSet.next()) {
                    idProjects.add(resultSet.getInt("project_id"));
                }
                for (Map.Entry<Project, Integer> project : setForUpdate.entrySet()) {
                    if (!idProjects.contains(project.getKey().getId())) {
                        ps.setInt(1, company.getId());
                        ps.setInt(2, project.getKey().getId());
                        ps.setInt(3, project.getValue());
                        ps.executeUpdate();
                    } else {
                        psUpShare.setInt(1, project.getValue());
                        psUpShare.setInt(2, company.getId());
                        psUpShare.setInt(3, project.getKey().getId());
                        psUpShare.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);
            PreparedStatement prepStatement = connection.prepareStatement(SELECT_PROJECTS_ID);
            PreparedStatement ps = connection.prepareStatement(DELETE_PROJECTS)){

            prepStatement.setInt(1, id);
            ResultSet rs = prepStatement.executeQuery();
            Set<Integer> idProjects = new HashSet<>();
            while (rs.next()) {
                idProjects.add(rs.getInt("project_id"));
            }
            for (Integer idProject : idProjects) {
                ps.setInt(1, id);
                ps.setInt(2, idProject);
                ps.addBatch();
            }
            ps.executeBatch();
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean existWithId(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        return rs.next();
    }

    private boolean existWithName(Connection connection, String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_NAME);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        return rs.next();
    }

    private void createCompanyProjectsRecords(Connection connection, Company company) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_COMPANY_PROJECT);
        for (Map.Entry<Project, Integer> project : company.getCompanyProjects().entrySet()) {
            statement.setInt(1, company.getId());
            statement.setInt(2, project.getKey().getId());
            statement.setInt(3, project.getValue());
            statement.executeUpdate();
        }
        statement.close();
    }

}
