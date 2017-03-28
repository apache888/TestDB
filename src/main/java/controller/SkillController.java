package controller;

import model.Model;
import view.ConsoleHelper;
import view.View;

import java.io.IOException;

/**
 * Create by Roman Hayda on 28.03.2017.
 */
public class SkillController implements Controller {
    private Model skillModel;
    private View skillView;

    public void onGetById() {
        while (true) {
            ConsoleHelper.writeToConsole("Input desired ID:");
            try {
                int id = Integer.parseInt(ConsoleHelper.readString());
                skillView.writeById(skillModel.getById(id));
                return;
            } catch (IOException e) {
                ConsoleHelper.writeToConsole("Wrong ID. Try again.\n");
            }
        }
    }

    public void onGetAll() {
        skillView.writeAll(skillModel.getAll());
    }

    public void setModel(Model skillModel) {
        this.skillModel = skillModel;
    }

    public void setView(View skillView) {
        this.skillView = skillView;
    }
}
