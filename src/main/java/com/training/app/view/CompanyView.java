package com.training.app.view;

import com.training.app.controller.Controller;
import com.training.app.controller.ProjectController;
import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;
import com.training.app.model.Company;
import com.training.app.model.Project;

import java.io.IOException;
import java.util.*;

/**
 * Create on 29.03.2017.
 * @author Roman Hayda
 *
 * Class implements interface View for Company object
 * class contains methods to fire CRUD events
 */
public class CompanyView implements View {
    private Controller<Company> controller;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void fireEventCreate() throws NotUniqueNameException, NotUniqueIdException {
        ConsoleHelper.writeToConsole("Creating Company object ...");
        controller.onCreate(createCompany());
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
        ConsoleHelper.writeToConsole("Creating Company object for update...");
        controller.onUpdate(createCompany());
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
     * @param company - Company object
     */
    private void writeById(Company company) {
        if (company == null || (company.getId() == 0 && company.getName() == null)){
            ConsoleHelper.writeToConsole("\nThere is no such ID\n");
        }else {
            ConsoleHelper.writeToConsole("\n" + company.toString() + "\n");
        }
    }

    /**
     *write to console information about received list of objects
     * @param list - list of Company objects
     */
    private void writeAll(List<Company> list) {
        if (list.isEmpty()) {
            ConsoleHelper.writeToConsole("\nThere are no records to org.training.app.view.\n");
        } else {
            ConsoleHelper.writeToConsole("\nAll records of table 'companies'\n");
            for (Company company : list) {
                ConsoleHelper.writeToConsole(company.toString());
            }
            ConsoleHelper.writeToConsole("\n");
        }
    }

    /**
     * create object by console dialog
     * @return Company object
     */
    private Company createCompany() {
        Company company;
        int id;
        String companyName;
        Map<Project, Integer> projects = new HashMap<>();

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
                ConsoleHelper.writeToConsole("Input company name:");
                companyName = ConsoleHelper.readString();
                break;
            } catch (IOException e) {
                ConsoleHelper.writeToConsole("Failed input. Try again");
            }
        }
        while (true) {
            try {
                ConsoleHelper.writeToConsole("Input project's IDs and ownership level of company: (for finish input 'exit')");
                String str;
                ProjectController projectController = new ProjectController();
                while (!(str = ConsoleHelper.readString()).equalsIgnoreCase("exit")) {
                    try {
                        int idProject = Integer.parseInt(str);
                        ConsoleHelper.writeToConsole("share:");
                        int shareProject = Integer.parseInt(ConsoleHelper.readString());
                        Project project = projectController.onGetById(idProject);
                        if (!projects.containsKey(project)) {
                            projects.put(project, shareProject);
                        }
                    } catch (NumberFormatException e) {
                        ConsoleHelper.writeToConsole("Wrong integer. Try again");
                    } catch (NoSuchIdException e) {
                        ConsoleHelper.writeToConsole(e.getMessage());
                    }
                }
                break;
            } catch (IOException e) {
                ConsoleHelper.writeToConsole(e.getMessage());
            }
        }
        company = new Company(id, companyName);
        company.setCompanyProjects(projects);

        return company;
    }
}
