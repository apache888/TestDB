package com.training.app.dao.jdbc;

import com.training.app.dao.DeveloperDao;
import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;
import com.training.app.model.Developer;
import com.training.app.model.Skill;

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

    private static final String SELECT_BY_ID = "SELECT * FROM developers WHERE id=?";
    private static final String SELECT_ALL = "SELECT * FROM developers";
    private static final String SELECT_BY_NAME = "SELECT * FROM developers WHERE name=?";
    private static final String DELETE_BY_ID = "DELETE FROM developers WHERE id=?";
    private static final String INSERT = "INSERT INTO developers VALUES (?, ?, ?, ?)";
    private static final String INSERT_AUTO_ID = "INSERT INTO developers (name, experience, salary) VALUES (?,?,?)";
    private static final String UPDATE_BY_ID = "UPDATE developers SET name=?, experience=?, salary=? WHERE id=?";

    private static final String SELECT_DEV_SKILLS_BY_ID = "SELECT skills.* FROM skills JOIN developers_skills on skill_id= skills.id and developer_id =?";
    private static final String INSERT_DEV_SKILL = "INSERT INTO developers_skills VALUES (?, ?)";
    private static final String SELECT_SKILLS_ID = "SELECT skill_id FROM developers_skills WHERE developer_id=?";
    private static final String DELETE_SKILLS = "DELETE FROM developers_skills WHERE developer_id=? AND skill_id=?";

    public void create(Developer developer) throws NotUniqueIdException, NotUniqueNameException {
        PreparedStatement prstmt;
        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD)){
            if (developer.getId() == 0 && !existWithName(connection, developer.getName())) {
                prstmt = connection.prepareStatement(INSERT_AUTO_ID);
                prstmt.setString(1, developer.getName());
                prstmt.setInt(2, developer.getExperience());
                prstmt.setInt(3, developer.getSalary());
                prstmt.executeUpdate();
                createDevSkillsRecords(connection, developer);
            } else if (existWithId(connection, developer.getId())) {
                throw new NotUniqueIdException("ID " + developer.getId() + " is not unique.");
            } else if (existWithName(connection, developer.getName())) {
                throw new NotUniqueNameException("Developer's name \'" + developer.getName() + "\' is not unique");
            } else {
                prstmt = connection.prepareStatement(INSERT);
                prstmt.setInt(1, developer.getId());
                prstmt.setString(2, developer.getName());
                prstmt.setInt(3, developer.getExperience());
                prstmt.setInt(4, developer.getSalary());
                prstmt.executeUpdate();
                createDevSkillsRecords(connection, developer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Developer getById(int id) throws NoSuchIdException {
        Developer developer = null;
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            PreparedStatement ps = connection.prepareStatement(SELECT_DEV_SKILLS_BY_ID)){

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int devId = resultSet.getInt("id");
                String devName = resultSet.getString("name");
                int exp = resultSet.getInt("experience");
                int salary = resultSet.getInt("salary");
                developer = new Developer(devId, devName);
                developer.setExperience(exp);
                developer.setSalary(salary);
            }
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Set<Skill> skills = new HashSet<>();
            while (rs.next()) {
                int skill_id = rs.getInt("id");
                String name = rs.getString("specialty");
//                skills.add(new Skill(skill_id, name));
            }
            rs.close();
            if (developer != null) {
                developer.setSkills(skills);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (developer != null) {
            return developer;
        } else {
            throw new NoSuchIdException("There is no record in \"developers\" with ID " + id + "\n");
        }

    }

    public List<Developer> getAll() {
        List<Developer> list = new ArrayList<Developer>();

        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            PreparedStatement stmt = connection.prepareStatement(SELECT_DEV_SKILLS_BY_ID);
            ResultSet resultSet = statement.executeQuery(SELECT_ALL)){

            while (resultSet.next()) {
                int devId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int exp = resultSet.getInt("experience");
                int salary = resultSet.getInt("salary");
                Developer developer = new Developer(devId, name);
                developer.setExperience(exp);
                developer.setSalary(salary);

                stmt.setInt(1, devId);
                ResultSet rs = stmt.executeQuery();
                Set<Skill> set = new HashSet<>();
                while (rs.next()) {
                    int skill_id = rs.getInt("id");
                    String specialty = rs.getString("specialty");
//                    set.add(new Skill(skill_id, specialty));
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

    public void update(Developer developer) throws NotUniqueNameException, NotUniqueIdException {
        try(Connection connection =DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            PreparedStatement prstmt = connection.prepareStatement(UPDATE_BY_ID);
            PreparedStatement pr = connection.prepareCall(SELECT_SKILLS_ID);
            PreparedStatement ps = connection.prepareCall(INSERT_DEV_SKILL);
            ResultSet rs = statement.executeQuery(SELECT_ALL)){
            List<Integer> idList = new ArrayList<>();
            while (rs.next()) {
                idList.add(rs.getInt("id"));
            }
            if (!idList.contains(developer.getId())) {
                create(developer);
            } else {
                prstmt.setString(1, developer.getName());
                prstmt.setInt(2, developer.getExperience());
                prstmt.setInt(3, developer.getSalary());
                prstmt.setInt(4, developer.getId());
                prstmt.executeUpdate();
                Set<Skill> setForUpdate = developer.getSkills();
                Set<Integer> idSkills = new HashSet<>();
                pr.setInt(1, developer.getId());
                ResultSet resultSet = pr.executeQuery();
                while (resultSet.next()) {
                    idSkills.add(resultSet.getInt("skill_id"));
                }
                for (Skill skill : setForUpdate) {
                    if (!idSkills.contains(skill.getId())) {
                        ps.setInt(1, developer.getId());
                        ps.setInt(2, skill.getId());
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
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SKILLS_ID);
            PreparedStatement ps = connection.prepareStatement(DELETE_SKILLS)){

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            Set<Integer> idSkills = new HashSet<>();
            while (rs.next()) {
                idSkills.add(rs.getInt("skill_id"));
            }
            for (Integer idSkill : idSkills) {
                ps.setInt(1, id);
                ps.setInt(2, idSkill);
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
        rs.next();
        return name.equalsIgnoreCase(rs.getString("name"));
    }

    //create relation records between developer and skills in suitable table in database
    private void createDevSkillsRecords(Connection connection, Developer developer) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_DEV_SKILL);
        for (Skill skill : developer.getSkills()) {
            statement.setInt(1, developer.getId());
            statement.setInt(2, skill.getId());
            statement.executeUpdate();
        }
        statement.close();
    }
}
