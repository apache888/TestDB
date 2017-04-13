package com.training.app.dao.jdbc;

import com.training.app.dao.SkillDao;
import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;
import com.training.app.model.Skill;

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
    private static final String SELECT_BY_NAME = "SELECT * FROM skills WHERE name=?";
    private static final String DELETE_BY_ID = "DELETE FROM skills WHERE id=";
    private static final String INSERT = "INSERT INTO skills VALUES (?, ?)";
    private static final String INSERT_AUTO_ID = "INSERT INTO skills (name) VALUES (?)";
    private static final String UPDATE_BY_ID = "UPDATE skills SET name = ? WHERE id=?";

    public void create(Skill skill) throws NotUniqueIdException, NotUniqueNameException {
        PreparedStatement prstmt = null;
        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD)){

            if (skill.getId() == 0 && !existWithName(connection, skill.getName())) {
                prstmt = connection.prepareStatement(INSERT_AUTO_ID);
                prstmt.setString(1, skill.getName());
                prstmt.executeUpdate();
            } else if (existWithId(connection, skill.getId())) {
                throw new NotUniqueIdException("ID " + skill.getId() + " not unique.");
            } else if (existWithName(connection, skill.getName())) {
                throw new NotUniqueNameException("Specialty \'" + skill.getName() + "\' not unique");
            } else {
                prstmt = connection.prepareStatement(INSERT);
                prstmt.setInt(1, skill.getId());
                prstmt.setString(2, skill.getName());
                prstmt.executeUpdate();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (prstmt != null) {
                try {
                    prstmt.close();
                } catch (SQLException e) {
                    //NOP
                }
            }
        }
    }

    public Skill getById(int id) throws NoSuchIdException {
        Skill skill = null;
        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_BY_ID + id)){

            while (resultSet.next()) {
                int skillId = resultSet.getInt("id");
                String specialty = resultSet.getString("name");
                skill = new Skill();
                skill.setId(skillId);
                skill.setName(specialty);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (skill != null) {
            return skill;
        } else {
            throw new NoSuchIdException("There is no record in \"skills\" with ID " + id + "\n");
        }
    }

    public List<Skill> getAll() {
        List<Skill> list = new ArrayList<>();

        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL)){

            Skill skill;
            while (resultSet.next()) {
                int skillId = resultSet.getInt("id");
                String specialty = resultSet.getString("specialty");
                skill = new Skill();
                skill.setId(skillId);
                skill.setName(specialty);
                list.add(skill);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(Skill skill) throws NotUniqueNameException, NotUniqueIdException {
        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            PreparedStatement prstmt = connection.prepareStatement(UPDATE_BY_ID);
            ResultSet rs = statement.executeQuery(SELECT_ALL)){

            List<Integer> idList = new ArrayList<>();
            while (rs.next()) {
                idList.add(rs.getInt("id"));
            }
            if (!idList.contains(skill.getId())) {
                create(skill);
            } else {
                    prstmt.setString(1, skill.getName());
                    prstmt.setInt(2, skill.getId());
                    prstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement()){
            statement.executeUpdate(DELETE_BY_ID + id);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //check if record with such 'id' exists in database
    private boolean existWithId(Connection connection, int id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(SELECT_BY_ID + id);
        return rs.next();
    }

    //check if record with such 'name' exists in database
    private boolean existWithName(Connection connection, String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_NAME);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        return rs.next();
    }

}
