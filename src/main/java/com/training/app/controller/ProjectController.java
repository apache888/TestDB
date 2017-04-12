package com.training.app.controller;

import com.training.app.dao.ProjectDao;
import com.training.app.dao.hibernate.HibernateProjectDaoImpl;
import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;
import com.training.app.model.Project;

import java.util.List;

/**
 * Create by Roman Hayda on 29.03.2017.
 */
public class ProjectController implements Controller<Project> {
//    private ProjectDao projectDao = new JdbcProjectDaoImpl();
    private ProjectDao projectDao = new HibernateProjectDaoImpl();

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
