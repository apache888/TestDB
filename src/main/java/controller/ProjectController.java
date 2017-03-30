package controller;

import dao.ProjectDao;
import dao.jdbc.JdbcProjectDaoImpl;
import model.Project;

import java.util.List;

/**
 * Create by Roman Hayda on 29.03.2017.
 */
public class ProjectController implements Controller<Project> {
    private ProjectDao projectDao = new JdbcProjectDaoImpl();

    @Override
    public void onCreate(Project project) {
        projectDao.create(project);
    }

    @Override
    public Project onGetById(int id) {
        return projectDao.getById(id);
    }

    @Override
    public List<Project> onGetAll() {
        return projectDao.getAll();
    }

    @Override
    public void onUpdate(Project project) {
        projectDao.update(project);
    }

    @Override
    public void onDelete(int id) {
        projectDao.delete(id);
    }
}
