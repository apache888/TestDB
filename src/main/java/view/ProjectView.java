package view;

import controller.Controller;
import controller.DeveloperController;
import exception.NoSuchIdException;
import exception.NotUniqueIdException;
import exception.NotUniqueNameException;
import model.Developer;
import model.Project;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Create by Roman Hayda on 29.03.2017.
 */
public class ProjectView implements View {
    private Controller<Project> controller;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void fireEventCreate() throws NotUniqueNameException, NotUniqueIdException {
        ConsoleHelper.writeToConsole("Creating Project object ...");
        controller.onCreate(createProject());
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
        ConsoleHelper.writeToConsole("Creating Project object for update...");
        controller.onUpdate(createProject());
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

    //write to console information about received object
    private void writeById(Project project) {
        if (project == null || (project.getId() == 0 && project.getName() == null)){
            ConsoleHelper.writeToConsole("\nThere is no such ID\n");
        }else {
            ConsoleHelper.writeToConsole("\n" + project.toString() + "\n");
        }
    }

    //write to console information about received list of objects
    private void writeAll(List<Project> list) {
        if (list.isEmpty()) {
            ConsoleHelper.writeToConsole("\nThere are no records to view.\n");
        } else {
            ConsoleHelper.writeToConsole("\nAll records of table 'projects'\n");
            for (Project proj : list) {
                ConsoleHelper.writeToConsole(proj.toString());
            }
            ConsoleHelper.writeToConsole("\n");
        }
    }

    // create object by console dialog
    private Project createProject() {
        Project project;
        int id;
        String projectName;
        int cost;
        Set<Developer> developers = new HashSet<>();

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
                ConsoleHelper.writeToConsole("Input project name:");
                projectName = ConsoleHelper.readString();
                break;
            } catch (IOException e) {
                ConsoleHelper.writeToConsole("Failed input. Try again");
            }
        }
        while (true) {
            try {
                ConsoleHelper.writeToConsole("Input cost of project:");
                cost = Integer.parseInt(ConsoleHelper.readString());
                break;
            } catch (IOException | NumberFormatException e) {
                ConsoleHelper.writeToConsole("Wrong integer. Try again");
            }
        }
        while (true) {
            try {
                ConsoleHelper.writeToConsole("Input developer's IDs of project: (for finish input 'exit')");
                String str;
                DeveloperController devController = new DeveloperController();
                Set<Integer> idSet = new HashSet<>();
                while (!(str = ConsoleHelper.readString()).equalsIgnoreCase("exit")) {
                    try {
                        idSet.add(Integer.parseInt(str));
                    } catch (NumberFormatException e) {
                        ConsoleHelper.writeToConsole("Wrong integer. Try again");
                    }
                }
                try {
                    for (Integer idDev : idSet) {
                        developers.add(devController.onGetById(idDev));
                    }
                } catch (NoSuchIdException e) {
                    ConsoleHelper.writeToConsole(e.getMessage());
                }
                break;
            } catch (IOException e) {
                ConsoleHelper.writeToConsole(e.getMessage());
            }
        }

        project = new Project(id, projectName);
        project.setCost(cost);
        project.setProjectDevelopers(developers);

        return project;
    }
}
