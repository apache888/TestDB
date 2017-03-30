package dao;

import model.Project;

import java.util.List;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
public interface ProjectDao {

    void create(Project project);

    Project getById(int id);

    List<Project> getAll();

    void update(Project project);

    void delete(int id);
}
