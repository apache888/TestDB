package view;

import controller.Controller;
import exception.NotUniqueIdException;
import exception.NotUniqueNameException;
import model.Skill;

import java.io.IOException;
import java.util.List;

/**
 * Create by Roman Hayda on 28.03.2017.
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
                writeById((Skill) controller.onGetById(id));
                return;
            } catch (IOException e) {
                ConsoleHelper.writeToConsole("Wrong ID. Try again.\n");
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

    private void writeById(Skill skill) {
        if (skill == null || (skill.getId() == 0 && skill.getName() == null)){
            ConsoleHelper.writeToConsole("\nThere is no such ID\n");
        }else {
            ConsoleHelper.writeToConsole("\n" + skill.toString() + "\n");
        }
    }

    private void writeAll(List<Skill> list) {
        if (list.isEmpty()) {
            ConsoleHelper.writeToConsole("\nThere are no records to view.\n");
        } else {
            ConsoleHelper.writeToConsole("\nAll records of table 'skills'\n");
            for (Skill skill : list) {
                ConsoleHelper.writeToConsole(skill.toString());
            }
            ConsoleHelper.writeToConsole("\n");
        }
    }

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
        return new Skill(id, specialty);
    }

//    private Skill updateSkill() {
//        int id;
//        while (true) {
//            try {
//                ConsoleHelper.writeToConsole("Input current or new id:");
//                id = Integer.parseInt(ConsoleHelper.readString());
//                break;
//            } catch (IOException | NumberFormatException e) {
//                ConsoleHelper.writeToConsole("Wrong integer. Try again");
//            }
//        }
//        String specialty;
//        while (true) {
//            try {
//                ConsoleHelper.writeToConsole("Input altered specialty:");
//                specialty = ConsoleHelper.readString();
//                break;
//            } catch (IOException e) {
//                ConsoleHelper.writeToConsole("Failed input. Try again");
//            }
//        }
//        return new Skill(id, specialty);
//    }
}
