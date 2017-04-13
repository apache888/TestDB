package com.training.app.view;

import com.training.app.controller.Controller;
import com.training.app.controller.SkillController;
import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;
import com.training.app.model.Developer;
import com.training.app.model.Skill;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Create on 28.03.2017.
 * @author Roman Hayda
 *
 * Class implements interface View for Developer object
 * class contains methods to fire CRUD events
 */
public class DeveloperView implements View {
    private Controller<Developer> controller;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void fireEventCreate() throws NotUniqueNameException, NotUniqueIdException {
        ConsoleHelper.writeToConsole("Creating Developer object ...");
        controller.onCreate(createDev());
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
        ConsoleHelper.writeToConsole("Creating Developer object for update ...");
         controller.onUpdate(createDev());
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
     * @param dev - Developer object
     */
    private void writeById(Developer dev) {
        if (dev == null || (dev.getId() == 0 && dev.getName() == null)){
            ConsoleHelper.writeToConsole("\nThere is no such ID\n");
        }else {
            ConsoleHelper.writeToConsole("\n" + dev.toString() + "\n");
        }
    }

    /**
     *write to console information about received list of objects
     * @param list - list of Developer objects
     */
    private void writeAll(List<Developer> list) {
        if (list.isEmpty()) {
            ConsoleHelper.writeToConsole("\nThere are no records to org.training.app.view.\n");
        } else {
            ConsoleHelper.writeToConsole("\nAll records of table 'developers'\n");
            for (Developer dev : list) {
                ConsoleHelper.writeToConsole(dev.toString());
            }
            ConsoleHelper.writeToConsole("\n");
        }
    }

    /**
     * create object by console dialog
     * @return Developer object
     */
    private Developer createDev() {
        Developer developer;
        int id;
        String devName;
        int exp;
        int salary;
        Set<Skill> skills = new HashSet<>();

        while (true) {
            try {
                ConsoleHelper.writeToConsole("Input id (if you want auto increment, input zero):");
                id = Integer.parseInt(ConsoleHelper.readString());
                break;
            } catch (IOException | NumberFormatException e) {
                ConsoleHelper.writeToConsole("Wrong integer. Try again");
            }
        }
        while (true) {
            try {
                ConsoleHelper.writeToConsole("Input name of developer:");
                devName = ConsoleHelper.readString();
                break;
            } catch (IOException e) {
                ConsoleHelper.writeToConsole("Failed input. Try again");
            }
        }
        while (true) {
            try {
                ConsoleHelper.writeToConsole("Input experience of developer:");
                exp = Integer.parseInt(ConsoleHelper.readString());
                break;
            } catch (IOException | NumberFormatException e) {
                ConsoleHelper.writeToConsole("Wrong integer. Try again");
            }
        }
        while (true) {
            try {
                ConsoleHelper.writeToConsole("Input salary of developer:");
                salary = Integer.parseInt(ConsoleHelper.readString());
                break;
            } catch (IOException | NumberFormatException e) {
                ConsoleHelper.writeToConsole("Wrong integer. Try again");
            }
        }
        while (true) {
            try {
                ConsoleHelper.writeToConsole("Input skill's IDs of developer: (for finish input 'exit')");
                String str;
                SkillController skillController = new SkillController();
                Set<Integer> idSet = new HashSet<>();
                while (!(str = ConsoleHelper.readString()).equalsIgnoreCase("exit")) {
                    try {
                        idSet.add(Integer.parseInt(str));
                    } catch (NumberFormatException e) {
                        ConsoleHelper.writeToConsole("Wrong integer. Try again");
                    }
                }
                try {
                    for (Integer idSkill : idSet) {
                        skills.add(skillController.onGetById(idSkill));
                    }
                } catch (NoSuchIdException e) {
                    ConsoleHelper.writeToConsole(e.getMessage());
                }
                break;
            } catch (IOException e) {
                ConsoleHelper.writeToConsole(e.getMessage());
            }
        }

        developer = new Developer(id, devName);
        developer.setExperience(exp);
        developer.setSalary(salary);
        developer.setSkills(skills);

        return developer;
    }
}
