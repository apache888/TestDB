package dao.jdbc;

import dao.CustomerDao;
import dao.ProjectDao;
import exception.NoSuchIdException;
import exception.NotUniqueIdException;
import exception.NotUniqueNameException;
import model.Customer;
import model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Create by Roman Hayda on 29.03.2017.
 */
public class JdbcCustomerDaoImpl implements CustomerDao {
    private static final String URL = "jdbc:mysql://localhost:3306/it_test_db?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final String SELECT_BY_ID = "SELECT * FROM customers WHERE id=?";
    private static final String SELECT_ALL = "SELECT * FROM customers";
    private static final String SELECT_BY_NAME = "SELECT * FROM customers WHERE name_customer=?";
    private static final String DELETE_BY_ID = "DELETE FROM customers WHERE id=?";
    private static final String INSERT = "INSERT INTO customers VALUES (?,?)";
    private static final String INSERT_AUTO_ID = "INSERT INTO customers (name_customer) VALUES (?)";
    private static final String UPDATE_BY_ID = "UPDATE customers SET name_customer=? WHERE id=?";


    private static final String SELECT_CUSTOMER_PROJECTS_BY_ID = "SELECT project_id FROM customers_projects WHERE customer_id =?";
    private static final String INSERT_CUSTOMER_PROJECT = "INSERT INTO customers_projects VALUES (?, ?)";
    private static final String SELECT_PROJECTS_ID = "SELECT project_id FROM customers_projects WHERE customer_id=?";
    private static final String DELETE_PROJECTS = "DELETE FROM customers_projects WHERE customer_id=? AND project_id=?";

    public void create(Customer customer) throws NotUniqueIdException, NotUniqueNameException {
        PreparedStatement prstmt;
        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD)){
            if (customer.getId() == 0 && !existWithName(connection, customer.getName())) {
                prstmt = connection.prepareStatement(INSERT_AUTO_ID);
                prstmt.setString(1, customer.getName());
                prstmt.executeUpdate();
                createCompanyProjectsRecords(connection, customer);
            } else if (existWithId(connection, customer.getId())) {
                throw new NotUniqueIdException("ID " + customer.getId() + " is not unique.");
            } else if (existWithName(connection, customer.getName())) {
                throw new NotUniqueNameException("Customer's name \'" + customer.getName() + "\' is not unique");
            } else {
                prstmt = connection.prepareStatement(INSERT);
                prstmt.setInt(1, customer.getId());
                prstmt.setString(2, customer.getName());
                prstmt.executeUpdate();
                createCompanyProjectsRecords(connection, customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Customer getById(int id) throws NoSuchIdException {
        Customer customer = null;
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            PreparedStatement ps = connection.prepareStatement(SELECT_CUSTOMER_PROJECTS_BY_ID)){

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int customerId = resultSet.getInt("id");
                String nameCustomer = resultSet.getString("name_customer");
                customer = new Customer(customerId, nameCustomer);
            }

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Set<Project> projects = new HashSet<>();
            ProjectDao projectDao = new JdbcProjectDaoImpl();
            while (rs.next()) {
                int projectId = rs.getInt("project_id");
                Project project = projectDao.getById(projectId);
                projects.add(project);
            }
            rs.close();
            resultSet.close();
            if (customer != null) {
                customer.setCustomerProjects(projects);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (customer != null) {
            return customer;
        } else {
                throw new NoSuchIdException("There is no record in \"customers\" with ID " + id + "\n");
        }
    }

    public List<Customer> getAll() {
        List<Customer> list = new ArrayList<>();

        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            PreparedStatement stmt = connection.prepareStatement(SELECT_CUSTOMER_PROJECTS_BY_ID);
            ResultSet resultSet = statement.executeQuery(SELECT_ALL)){

            while (resultSet.next()) {
                int customerId = resultSet.getInt("id");
                String nameCustomer = resultSet.getString("name_customer");
                Customer customer = new Customer(customerId, nameCustomer);
                stmt.setInt(1, customerId);
                ResultSet rs = stmt.executeQuery();
                Set<Project> projects = new HashSet<>();
                ProjectDao projectDao = new JdbcProjectDaoImpl();
                while (rs.next()) {
                    int projectId = rs.getInt("project_id");
                    Project project = projectDao.getById(projectId);
                    projects.add(project);
                }
                rs.close();
                customer.setCustomerProjects(projects);
                list.add(customer);
            }
            resultSet.close();
        } catch (SQLException | NoSuchIdException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(Customer customer) throws NotUniqueIdException, NotUniqueNameException {
        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            PreparedStatement prstmt = connection.prepareStatement(UPDATE_BY_ID);
            PreparedStatement pr = connection.prepareStatement(SELECT_PROJECTS_ID);
            PreparedStatement ps = connection.prepareStatement(INSERT_CUSTOMER_PROJECT);
            ResultSet rs = statement.executeQuery(SELECT_ALL)){

            List<Integer> idList = new ArrayList<>();
            while (rs.next()) {
                idList.add(rs.getInt("id"));
            }
            if (!idList.contains(customer.getId())) {
                create(customer);
            } else {
                prstmt.setString(1, customer.getName());
                prstmt.setInt(2, customer.getId());
                prstmt.executeUpdate();
                Set<Project> setForUpdate = customer.getCustomerProjects();
                Set<Integer> idProjects = new HashSet<>();
                pr.setInt(1, customer.getId());
                ResultSet resultSet = pr.executeQuery();
                while (resultSet.next()) {
                    idProjects.add(resultSet.getInt("project_id"));
                }
                for (Project project : setForUpdate) {
                    if (!idProjects.contains(project.getId())) {
                        ps.setInt(1, customer.getId());
                        ps.setInt(2, project.getId());
                        ps.executeUpdate();
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

    //check if record with such 'id' exists in database
    private boolean existWithId(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        return rs.next();
    }

    //check if record with such 'name' exists in database
    private boolean existWithName(Connection connection, String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_NAME);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        return rs.next();
    }

    //create relation records between customer and projects in suitable table in database
    private void createCompanyProjectsRecords(Connection connection, Customer customer) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_CUSTOMER_PROJECT);
        for (Project project : customer.getCustomerProjects()) {
            statement.setInt(1, customer.getId());
            statement.setInt(2, project.getId());
            statement.executeUpdate();
        }
        statement.close();
    }

}
