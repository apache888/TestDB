package dao.jdbc;

import dao.ProjectDao;
import exception.NoSuchIdException;
import exception.NotUniqueIdException;
import exception.NotUniqueNameException;
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
    private static final String SELECT_BY_NAME = "SELECT * FROM projects WHERE name_project=?";
    private static final String DELETE_BY_ID = "DELETE FROM projects WHERE id=";
    private static final String INSERT = "INSERT INTO projects VALUES (?,?,?)";
    private static final String INSERT_AUTO_ID = "INSERT INTO projects (name_project, cost) VALUES (?,?)";
    private static final String UPDATE_BY_ID = "UPDATE projects SET name_project=?, cost=? WHERE id=?";


    private static final String SELECT_PROJECT_DEVS_BY_ID = "SELECT developers.* FROM developers JOIN projects_developers on developer_id= developers.id and project_id =";
    private static final String INSERT_PROJECT_DEV = "INSERT INTO projects_developers VALUES (?, ?)";
    private static final String SELECT_DEVS_ID = "SELECT developer_id FROM projects_developers WHERE project_id=?";
    private static final String DELETE_DEVS = "DELETE FROM projects_developers WHERE project_id=? AND developer_id=?";

    public void create(Project project) throws NotUniqueIdException, NotUniqueNameException {
        PreparedStatement prstmt;
        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD)){
            if (project.getId() == 0 && !existWithName(connection, project.getName())) {
                prstmt = connection.prepareStatement(INSERT_AUTO_ID);
                prstmt.setString(1, project.getName());
                prstmt.setInt(2, project.getCost());
                prstmt.executeUpdate();
                createProjectDevsRecords(connection, project);
            } else if (existWithId(connection, project.getId())) {
                throw new NotUniqueIdException("ID " + project.getId() + " is not unique.");
            } else if (existWithName(connection, project.getName())) {
                throw new NotUniqueNameException("Project's name \'" + project.getName() + "\' is not unique");
            } else {
                prstmt = connection.prepareStatement(INSERT);
                prstmt.setInt(1, project.getId());
                prstmt.setString(2, project.getName());
                prstmt.setInt(3, project.getCost());
                prstmt.executeUpdate();
                createProjectDevsRecords(connection, project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Project getById(int id) throws NoSuchIdException {
        Project project = null;
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_BY_ID + id)){

            while (resultSet.next()) {
                int projId = resultSet.getInt("id");
                String nameProject = resultSet.getString("name_project");
                int cost = resultSet.getInt("cost");
                project = new Project(projId, nameProject);
                project.setCost(cost);
            }
            ResultSet rs = statement.executeQuery(SELECT_PROJECT_DEVS_BY_ID + id);
            Set<Developer> set = new HashSet<>();
            while (rs.next()) {
                int devId = rs.getInt("id");
                String nameDev = rs.getString("name_dev");
                set.add(new Developer(devId, nameDev));
            }
            rs.close();
            if (project != null) {
                project.setProjectDevelopers(set);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (project != null) {
            return project;
        } else {
            throw new NoSuchIdException("\nThere is no record in \"projects\" with ID " + id + "\n");
        }
    }

    public List<Project> getAll() {
        List<Project> list = new ArrayList<>();

        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL)){

            while (resultSet.next()) {
                int projId = resultSet.getInt("id");
                String nameProject = resultSet.getString("name_project");
                int cost = resultSet.getInt("cost");
                Project project = new Project(projId, nameProject);
                project.setCost(cost);

                ResultSet rs = stmt.executeQuery(SELECT_PROJECT_DEVS_BY_ID + projId);
                Set<Developer> set = new HashSet<>();
                while (rs.next()) {
                    int devId = rs.getInt("id");
                    String nameDev = rs.getString("name_dev");
                    set.add(new Developer(devId, nameDev));
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

    public void update(Project project) throws NotUniqueNameException, NotUniqueIdException {
        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            PreparedStatement prstmt = connection.prepareStatement(UPDATE_BY_ID);
            PreparedStatement pr = connection.prepareCall(SELECT_DEVS_ID);
            PreparedStatement ps = connection.prepareCall(INSERT_PROJECT_DEV);
            ResultSet rs = statement.executeQuery(SELECT_ALL)){
            List<Integer> idList = new ArrayList<>();
            while (rs.next()) {
                idList.add(rs.getInt("id"));
            }
            if (!idList.contains(project.getId())) {
                create(project);
            } else {
                prstmt.setString(1, project.getName());
                prstmt.setInt(2, project.getCost());
                prstmt.setInt(3, project.getId());
                prstmt.executeUpdate();
                Set<Developer> setForUpdate = project.getProjectDevelopers();
                Set<Integer> idDevs = new HashSet<>();
                pr.setInt(1, project.getId());
                ResultSet resultSet = pr.executeQuery();
                while (resultSet.next()) {
                    idDevs.add(resultSet.getInt("developer_id"));
                }
                for (Developer dev : setForUpdate) {
                    if (!idDevs.contains(dev.getId())) {
                        ps.setInt(1, project.getId());
                        ps.setInt(2, dev.getId());
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
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEVS_ID);
            PreparedStatement ps = connection.prepareStatement(DELETE_DEVS)){

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            Set<Integer> idDevs = new HashSet<>();
            while (rs.next()) {
                idDevs.add(rs.getInt("developer_id"));
            }
            for (Integer idDev : idDevs) {
                ps.setInt(1, id);
                ps.setInt(2, idDev);
                ps.addBatch();
            }
            ps.executeBatch();
            statement.executeUpdate(DELETE_BY_ID + id);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean existWithId(Connection connection, int id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(SELECT_BY_ID + id);
        return rs.next();
    }

    private boolean existWithName(Connection connection, String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_NAME);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        return rs.next();
    }

    private void createProjectDevsRecords(Connection connection, Project project) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_PROJECT_DEV);
        for (Developer dev : project.getProjectDevelopers()) {
            statement.setInt(1, project.getId());
            statement.setInt(2, dev.getId());
            statement.executeUpdate();
//            statement.addBatch();
        }
//        statement.executeBatch();
        statement.close();
    }
}
