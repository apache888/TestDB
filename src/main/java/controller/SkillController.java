package controller;

import model.Model;
import view.View;

/**
 * Create by Roman Hayda on 28.03.2017.
 */
public class SkillController implements Controller {
    private Model skillModel;
    private View skillView;

    @Override
    public void onCreate() {
        skillModel.create();
    }

    public void onGetById(int id) {
       skillView.writeById(skillModel.getById(id));
    }

    public void onGetAll() {
        skillView.writeAll(skillModel.getAll());
    }

    @Override
    public void onUpdate(int id) {
        skillModel.update(id);
    }

    @Override
    public void onDelete(int id) {
        skillModel.delete(id);
    }

    public void setModel(Model skillModel) {
        this.skillModel = skillModel;
    }

    public void setView(View skillView) {
        this.skillView = skillView;
    }
}
