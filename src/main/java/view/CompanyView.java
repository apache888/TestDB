package view;

import controller.Controller;
import exception.NotUniqueIdException;
import exception.NotUniqueNameException;
import model.Company;

import java.io.IOException;
import java.util.List;

/**
 * Create by Roman Hayda on 29.03.2017.
 */
public class CompanyView implements View {
    private Controller<Company> controller;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void fireEventCreate() throws NotUniqueNameException, NotUniqueIdException {
        Company company = null;
        controller.onCreate(company);
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
            }
        }
    }

    @Override
    public void fireEventGetAll() {
        writeAll(controller.onGetAll());
    }

    @Override
    public void fireEventUpdate() throws NotUniqueNameException, NotUniqueIdException {
        Company company = null;
        while (true) {
            ConsoleHelper.writeToConsole("Input desired ID:");
            try {
                int id = Integer.parseInt(ConsoleHelper.readString());
                controller.onUpdate(company);
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

    private void writeById(Company company) {
        if (company == null || (company.getId() == 0 && company.getName() == null)){
            ConsoleHelper.writeToConsole("\nThere is no such ID\n");
        }else {
            ConsoleHelper.writeToConsole("\n" + company.toString() + "\n");
        }
    }

    private void writeAll(List<Company> list) {
        if (list.isEmpty()) {
            ConsoleHelper.writeToConsole("\nThere are no records to view.\n");
        } else {
            ConsoleHelper.writeToConsole("\nAll records of table 'companies'\n");
            for (Company company : list) {
                ConsoleHelper.writeToConsole(company.toString());
            }
            ConsoleHelper.writeToConsole("\n");
        }
    }
}
