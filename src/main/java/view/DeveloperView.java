package view;

import controller.Controller;
import model.entities.BaseObject;
import model.entities.Developer;

import java.util.List;

/**
 * Create by Roman Hayda on 28.03.2017.
 */
public class DeveloperView implements View {
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void fireEventGetById() {
        controller.onGetById();
    }

    public void fireEventGetAll() {
        controller.onGetAll();
    }

    public void writeById(BaseObject dev) {
        if (dev == null || (dev.getId() == 0 && dev.getName() == null)){
            ConsoleHelper.writeToConsole("\nThere is no such ID\n");
        }else {
            ConsoleHelper.writeToConsole("\n" + dev.toString() + "\n");
        }
    }

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
