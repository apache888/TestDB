package dao.jdbc;

import dao.DeveloperDao;
import model.Developer;
import model.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Create by Roman Hayda on 27.03.2017.
 */
public class JdbcDeveloperDaoImpl implements DeveloperDao {
    private static final String URL = "jdbc:mysql://localhost:3306/it_test_db?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final String SELECT_BY_ID = "SELECT * FROM developers WHERE id=";
    private static final String SELECT_ALL = "SELECT * FROM developers";
    private static final String DELETE_BY_ID = "DELETE FROM developers WHERE id=";

    private static final String SELECT_DEV_SKILLS_BY_ID = "SELECT skills.* FROM skills JOIN developers_skills on skill_id= skills.id and developer_id =";

    public void create(Developer developer) {

    }

    public Developer getById(int id) {
        Developer developer = null;
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_BY_ID + id)){

            while (resultSet.next()) {
                int dev_id = resultSet.getInt("id");
                String name = resultSet.getString("name_dev");
                int exp = resultSet.getInt("experience");
                int salary = resultSet.getInt("salary");
                developer = new Developer(dev_id, name);
                developer.setExperience(exp);
                developer.setSalary(salary);
            }
            ResultSet rs = statement.executeQuery(SELECT_DEV_SKILLS_BY_ID + id);
            Set<Skill> set = new HashSet<>();
            while (rs.next()) {
                int skill_id = rs.getInt("id");
                String name = rs.getString("specialty");
                set.add(new Skill(skill_id, name));
            }
            rs.close();
            developer.setSkills(set);
            return developer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer;
    }

    public List<Developer> getAll() {
        List<Developer> list = new ArrayList<Developer>();

        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL)){

            while (resultSet.next()) {
                int dev_id = resultSet.getInt("id");
                String name = resultSet.getString("name_dev");
                int exp = resultSet.getInt("experience");
                int salary = resultSet.getInt("salary");
                Developer developer = new Developer(dev_id, name);
                developer.setExperience(exp);
                developer.setSalary(salary);

                ResultSet rs = statement.executeQuery(SELECT_DEV_SKILLS_BY_ID + dev_id);
                Set<Skill> set = new HashSet<>();
                while (rs.next()) {
                    int skill_id = rs.getInt("id");
                    String specialty = rs.getString("specialty");
                    set.add(new Skill(skill_id, specialty));
                }
                rs.close();
                developer.setSkills(set);
                list.add(developer);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(Developer developer) {

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
