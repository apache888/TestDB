package com.training.app.view;

import com.training.app.controller.Controller;
import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;
import com.training.app.model.Skill;

import java.io.IOException;
import java.util.List;

/**
 * Create on 28.03.2017.
 * @author Roman Hayda
 *
 * Class implements interface View for Skill object
 * class contains methods to fire CRUD events
 */
public class SkillView implements View {
    private Controller<Skill> controller;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void fireEventCreate() throws NotUniqueNameException, NotUniqueIdException {
        ConsoleHelper.writeToConsole("Creating Skill object ...");
        controller.onCreate(createSkill());
    }

    @Override
    public void fireEventGetById() {
        while (true) {
            ConsoleHelper.writeToConsole("Input desired ID:");
            try {
                int id = Integer.parseInt(ConsoleHelper.readString());
                writeById(controller.onGetById(id));
                return;
            } catch (IOException e) {
                ConsoleHelper.writeToConsole("Wrong ID. Try again.\n");
            } catch (NoSuchIdException e) {
                ConsoleHelper.writeToConsole(e.getMessage());
            }
        }
    }

    @Override
    public void fireEventGetAll() {
        writeAll(controller.onGetAll());
    }

    @Override
    public void fireEventUpdate() throws NotUniqueNameException, NotUniqueIdException {
        ConsoleHelper.writeToConsole("Creating Skill object for update ...");
       controller.onUpdate(createSkill());
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

    /**
     * write to console information about received object
     * @param skill - Skill object
     */
    private void writeById(Skill skill) {
        if (skill == null || (skill.getId() == 0 && skill.getName() == null)){
            ConsoleHelper.writeToConsole("\nThere is no such ID\n");
        }else {
            ConsoleHelper.writeToConsole("\n" + skill.toString() + "\n");
        }
    }

    /**
     * write to console information about received list of objects
     * @param list - list of Skill objects
     */
    private void writeAll(List<Skill> list) {
        if (list.isEmpty()) {
            ConsoleHelper.writeToConsole("\nThere are no records to org.training.app.view.\n");
        } else {
            ConsoleHelper.writeToConsole("\nAll records of table 'skills'\n");
            for (Skill skill : list) {
                ConsoleHelper.writeToConsole(skill.toString());
            }
            ConsoleHelper.writeToConsole("\n");
        }
    }

    /**
     * create object by console dialog
     * @return Skill object
     */
    private Skill createSkill() {
        int id;
        while (true) {
            try {
                ConsoleHelper.writeToConsole("Input id (if you want auto increment, input zero):");
                id = Integer.parseInt(ConsoleHelper.readString());
                break;
            } catch (IOException | NumberFormatException e) {
                ConsoleHelper.writeToConsole("Wrong integer. Try again");
            }
        }
        String specialty;
        while (true) {
            try {
                ConsoleHelper.writeToConsole("Input specialty:");
                specialty = ConsoleHelper.readString();
                break;
            } catch (IOException e) {
                ConsoleHelper.writeToConsole("Failed input. Try again");
            }
        }
        Skill skill = new Skill();
        skill.setName(specialty);
        skill.setId(id);
        return skill;
    }
}
