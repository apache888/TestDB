package view;

import controller.Controller;
import model.entities.BaseObject;
import model.entities.Skill;

import java.util.List;

/**
 * Create by Roman Hayda on 28.03.2017.
 */
public class SkillView implements View {
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

    public void writeById(BaseObject skill) {
        if (skill == null || (skill.getId() == 0 && skill.getName() == null)){
            ConsoleHelper.writeToConsole("\nThere is no such ID\n");
        }else {
            ConsoleHelper.writeToConsole("\n" + skill.toString() + "\n");
        }
    }

    public void writeAll(List<? extends BaseObject> list) {
        if (list.isEmpty()) {
            ConsoleHelper.writeToConsole("\nThere are no records to view.\n");
        } else {
            ConsoleHelper.writeToConsole("\nAll records of table 'skills'\n");
            for (BaseObject object : list) {
                Skill skill = (Skill) object;
                ConsoleHelper.writeToConsole(skill.toString());
            }
            ConsoleHelper.writeToConsole("\n");
        }
    }
    
}
