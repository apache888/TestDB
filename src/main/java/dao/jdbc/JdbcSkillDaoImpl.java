package dao.jdbc;

import dao.SkillDao;
import model.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by Roman Hayda on 27.03.2017.
 */
public class JdbcSkillDaoImpl implements SkillDao {
    private static final String URL = "jdbc:mysql://localhost:3306/it_test_db?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final String SELECT_BY_ID = "SELECT * FROM skills WHERE id=";
    private static final String SELECT_ALL = "SELECT * FROM skills";
    private static final String DELETE_BY_ID = "DELETE FROM skills WHERE id=";
    private static final String INSERT = "INSERT INTO skills (specialty) VALUES (?)";

    public void create(Skill skill) {
//        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            PreparedStatement prstmt = connection.prepareStatement(INSERT)){
//            while (true) {
//                try {
//                    ConsoleHelper.writeToConsole("Input new specialty:");
//                    prstmt.setString(1, ConsoleHelper.readString());
//                    break;
//                } catch (IOException e) {
//                    ConsoleHelper.writeToConsole("Wrong input. Try again.\n");
//                }
//            }
//            prstmt.executeUpdate();
//        }catch (SQLException e) {
//            e.printStackTrace();
//        }
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

    public void update(Skill skill) {

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
