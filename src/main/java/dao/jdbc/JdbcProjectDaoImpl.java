package dao.jdbc;

import dao.DeveloperDao;
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

    private static final String SELECT_BY_ID = "SELECT * FROM projects WHERE id=?";
    private static final String SELECT_ALL = "SELECT * FROM projects";
    private static final String SELECT_BY_NAME = "SELECT * FROM projects WHERE name_project=?";
    private static final String DELETE_BY_ID = "DELETE FROM projects WHERE id=?";
    private static final String INSERT = "INSERT INTO projects VALUES (?,?,?)";
    private static final String INSERT_AUTO_ID = "INSERT INTO projects (name_project, cost) VALUES (?,?)";
    private static final String UPDATE_BY_ID = "UPDATE projects SET name_project=?, cost=? WHERE id=?";


    private static final String SELECT_PROJECT_DEVS_BY_ID = "SELECT developers.* FROM developers JOIN projects_developers on developer_id= developers.id and project_id =?";
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
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            PreparedStatement ps = connection.prepareStatement(SELECT_PROJECT_DEVS_BY_ID)){

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int projectId = resultSet.getInt("id");
                String nameProject = resultSet.getString("name_project");
                int cost = resultSet.getInt("cost");
                project = new Project(projectId, nameProject);
                project.setCost(cost);
            }
            ps.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            Set<Developer> developers = new HashSet<>();
            DeveloperDao developerDao = new JdbcDeveloperDaoImpl();
            while (rs.next()) {
                int devId = rs.getInt("id");
                Developer developer = developerDao.getById(devId);
                developers.add(developer);
            }
            rs.close();
            resultSet.close();
            if (project != null) {
                project.setProjectDevelopers(developers);
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
            PreparedStatement ps = connection.prepareStatement(SELECT_PROJECT_DEVS_BY_ID);
            ResultSet resultSet = statement.executeQuery(SELECT_ALL)){

            while (resultSet.next()) {
                int projectId = resultSet.getInt("id");
                String nameProject = resultSet.getString("name_project");
                int cost = resultSet.getInt("cost");
                Project project = new Project(projectId, nameProject);
                project.setCost(cost);

                ps.setInt(1, projectId);
                ResultSet rs = ps.executeQuery();
                Set<Developer> set = new HashSet<>();
                DeveloperDao developerDao = new JdbcDeveloperDaoImpl();
                while (rs.next()) {
                    int devId = rs.getInt("id");
                    Developer developer = developerDao.getById(devId);
                    set.add(developer);
                }
                rs.close();
                project.setProjectDevelopers(set);
                list.add(project);
            }
            resultSet.close();
        } catch (SQLException | NoSuchIdException e) {
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
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);
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

    //create relation records between project and developers in suitable table in database
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
