package dao.jdbc;

import model.entities.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by Roman Hayda on 27.03.2017.
 */
public class JdbcSkillDaoImpl {
    private static final String URL = "jdbc:mysql://localhost:3306/it_test_db?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final String SELECT_BY_ID = "SELECT * FROM skills WHERE id=";
    private static final String SELECT_ALL = "SELECT * FROM skills";

    public void create() {

    }

    public Skill getById(int id) {
        Skill skill = null;
        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_BY_ID + id)){

            while (resultSet.next()) {
                int skill_id = resultSet.getInt("id");
                String name = resultSet.getString("specialty");
                skill = new Skill(skill_id, name);
            }
            return skill;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skill;
    }

    public List<Skill> getAll() {
        List<Skill> list = new ArrayList<Skill>();

        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL)){

            Skill skill;
            while (resultSet.next()) {
                int skill_id = resultSet.getInt("id");
                String name = resultSet.getString("specialty");
                skill = new Skill(skill_id, name);
                list.add(skill);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(int id) {

    }

    public void delete(int id) {

    }
}
