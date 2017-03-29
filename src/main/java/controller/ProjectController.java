package controller;

import model.Model;
import view.View;

/**
 * Create by Roman Hayda on 29.03.2017.
 */
public class ProjectController implements Controller {
    private Model model;
    private View view;

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void onCreate() {
        model.create();
    }

    @Override
    public void onGetById(int id) {
        view.writeById(model.getById(id));
    }

    @Override
    public void onGetAll() {
        view.writeAll(model.getAll());
    }

    @Override
    public void onUpdate(int id) {
        model.update(id);
    }

    @Override
    public void onDelete(int id) {
        model.delete(id);
    }
}
