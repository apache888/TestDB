package model;

import dao.ProjectDao;
import model.entities.Project;

import java.util.List;

/**
 * Create by Roman Hayda on 29.03.2017.
 */
public class ProjectModel implements Model {
    private ProjectDao projectDao = new ProjectDao();

    @Override
    public void create() {
        projectDao.create();
    }

    @Override
    public Project getById(int id) {
        return projectDao.getById(id);
    }

    @Override
    public List<Project> getAll() {
        return projectDao.getAll();
    }

    @Override
    public void update(int id) {
        projectDao.update(id);
    }

    @Override
    public void delete(int id) {
        projectDao.delete(id);
    }
}
