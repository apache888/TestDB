package controller;

import dao.ProjectDao;
import dao.jdbc.JdbcProjectDaoImpl;
import exception.NoSuchIdException;
import exception.NotUniqueIdException;
import exception.NotUniqueNameException;
import model.Project;

import java.util.List;

/**
 * Create by Roman Hayda on 29.03.2017.
 */
public class ProjectController implements Controller<Project> {
    private ProjectDao projectDao = new JdbcProjectDaoImpl();

    @Override
    public void onCreate(Project project) throws NotUniqueNameException, NotUniqueIdException {
        projectDao.create(project);
    }

    @Override
    public Project onGetById(int id) throws NoSuchIdException {
        return projectDao.getById(id);
    }

    @Override
    public List<Project> onGetAll() {
        return projectDao.getAll();
    }

    @Override
    public void onUpdate(Project project) throws NotUniqueNameException, NotUniqueIdException {
        projectDao.update(project);
    }

    @Override
    public void onDelete(int id) {
        projectDao.delete(id);
    }
}
