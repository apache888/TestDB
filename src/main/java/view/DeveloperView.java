package view;

import controller.Controller;
import model.entities.BaseObject;
import model.entities.Developer;

import java.io.IOException;
import java.util.List;

/**
 * Create by Roman Hayda on 28.03.2017.
 */
public class DeveloperView implements View {
    private Controller controller;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void fireEventCreate() {
        controller.onCreate();
    }

    @Override
    public void fireEventGetById() {
        while (true) {
            ConsoleHelper.writeToConsole("Input desired ID:");
            try {
                int id = Integer.parseInt(ConsoleHelper.readString());
                controller.onGetById(id);
                return;
            } catch (IOException e) {
                ConsoleHelper.writeToConsole("Wrong ID. Try again.\n");
            }
        }
    }

    @Override
    public void fireEventGetAll() {
        controller.onGetAll();
    }

    @Override
    public void fireEventUpdate() {
        while (true) {
            ConsoleHelper.writeToConsole("Input desired ID:");
            try {
                int id = Integer.parseInt(ConsoleHelper.readString());
                controller.onUpdate(id);
                return;
            } catch (IOException e) {
                ConsoleHelper.writeToConsole("Wrong ID. Try again.\n");
            }
        }
    }

    @Override
    public void fireEventDelete() {
        while (true) {
            ConsoleHelper.writeToConsole("Input desired ID:");
            try {
                int id = Integer.parseInt(ConsoleHelper.readString());
                controller.onDelete(id);
                return;
            } catch (IOException e) {
                ConsoleHelper.writeToConsole("Wrong ID. Try again.\n");
            }
        }
    }

    @Override
    public void writeById(BaseObject dev) {
        if (dev == null || (dev.getId() == 0 && dev.getName() == null)){
            ConsoleHelper.writeToConsole("\nThere is no such ID\n");
        }else {
            ConsoleHelper.writeToConsole("\n" + dev.toString() + "\n");
        }
    }

    @Override
    public void writeAll(List<? extends BaseObject> list) {
        if (list.isEmpty()) {
            ConsoleHelper.writeToConsole("\nThere are no records to view.\n");
        } else {
            ConsoleHelper.writeToConsole("\nAll records of table 'developers'\n");
            for (BaseObject object : list) {
                Developer dev = (Developer) object;
                ConsoleHelper.writeToConsole(dev.toString());
            }
            ConsoleHelper.writeToConsole("\n");
        }
    }
}
