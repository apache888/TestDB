package dao.jdbc;

import dao.ProjectDao;
import model.Developer;
import model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Create by Roman Hayda on 29.03.2017.
 */
public class JdbcProjectDaoImpl implements ProjectDao {
    private static final String URL = "jdbc:mysql://localhost:3306/it_test_db?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final String SELECT_BY_ID = "SELECT * FROM projects WHERE id=";
    private static final String SELECT_ALL = "SELECT * FROM projects";
    private static final String DELETE_BY_ID = "DELETE FROM projects WHERE id=";

    private static final String SELECT_PROJECT_DEVS_BY_ID = "SELECT developers.* FROM developers JOIN projects_developers on developer_id= developers.id and project_id =";

    public void create(Project project) {

    }

    public Project getById(int id) {
        Project project = null;
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_BY_ID + id)){

            while (resultSet.next()) {
                int proj_id = resultSet.getInt("id");
                String name = resultSet.getString("name_project");
                int cost = resultSet.getInt("cost");
                project = new Project(proj_id, name);
                project.setCost(cost);
            }
            ResultSet rs = statement.executeQuery(SELECT_PROJECT_DEVS_BY_ID + id);
            Set<Developer> set = new HashSet<>();
            while (rs.next()) {
                int dev_id = rs.getInt("id");
                String name = rs.getString("name_dev");
                set.add(new Developer(dev_id, name));
            }
            rs.close();
            project.setProjectDevelopers(set);
            return project;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    public List<Project> getAll() {
        List<Project> list = new ArrayList<>();

        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL)){

            while (resultSet.next()) {
                int proj_id = resultSet.getInt("id");
                String name = resultSet.getString("name_project");
                int cost = resultSet.getInt("cost");
                Project project = new Project(proj_id, name);
                project.setCost(cost);

                ResultSet rs = statement.executeQuery(SELECT_PROJECT_DEVS_BY_ID + proj_id);
                Set<Developer> set = new HashSet<>();
                while (rs.next()) {
                    int dev_id = rs.getInt("id");
                    String name_dev = rs.getString("name_dev");
                    set.add(new Developer(dev_id, name_dev));
                }
                rs.close();
                project.setProjectDevelopers(set);
                list.add(project);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(Project project) {

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
