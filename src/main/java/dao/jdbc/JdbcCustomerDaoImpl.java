package dao.jdbc;

import dao.CustomerDao;
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

    private static final String SELECT_BY_ID = "SELECT * FROM customers WHERE id=";
    private static final String SELECT_ALL = "SELECT * FROM customers";
    private static final String DELETE_BY_ID = "DELETE FROM customers WHERE id=";

    private static final String SELECT_CUSTOMER_PROJECTS_BY_ID = "SELECT projects.* FROM projects JOIN customers_projects on project_id= projects.id and customer_id =";

    public void create(Customer customer) {

    }

    public Customer getById(int id) {
        Customer customer = null;
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_BY_ID + id)){

            while (resultSet.next()) {
                int cust_id = resultSet.getInt("id");
                String name = resultSet.getString("name_customer");
                customer = new Customer(cust_id, name);
            }
            ResultSet rs = statement.executeQuery(SELECT_CUSTOMER_PROJECTS_BY_ID + id);
            Set<Project> set = new HashSet<>();
            while (rs.next()) {
                int proj_id = rs.getInt("id");
                String name = rs.getString("name_project");
                set.add(new Project(proj_id, name));
            }
            rs.close();
            customer.setCustomerProjects(set);
            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public List<Customer> getAll() {
        List<Customer> list = new ArrayList<>();

        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL)){

            while (resultSet.next()) {
                int cust_id = resultSet.getInt("id");
                String name = resultSet.getString("name_customer");
                Customer customer = new Customer(cust_id, name);

                ResultSet rs = stmt.executeQuery(SELECT_CUSTOMER_PROJECTS_BY_ID + cust_id);
                Set<Project> set = new HashSet<>();
                while (rs.next()) {
                    int proj_id = rs.getInt("id");
                    String name_dev = rs.getString("name_project");
                    set.add(new Project(proj_id, name_dev));
                }
                rs.close();
                customer.setCustomerProjects(set);
                list.add(customer);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(Customer customer) {

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
