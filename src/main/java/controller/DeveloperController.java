package controller;

import model.Model;
import view.ConsoleHelper;
import view.View;

import java.io.IOException;

/**
 * Create by Roman Hayda on 28.03.2017.
 */
public class DeveloperController implements Controller {
    private Model model;
    private View view;

    public void setView(View view) {
        this.view = view;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void onGetById() {
        while (true) {
            ConsoleHelper.writeToConsole("Input desired ID:");
            try {
                int id = Integer.parseInt(ConsoleHelper.readString());
                view.writeById(model.getById(id));
                return;
            } catch (IOException e) {
                ConsoleHelper.writeToConsole("Wrong ID. Try again.\n");
            }
        }
    }

    public void onGetAll() {
        view.writeAll(model.getAll());
    }
}
