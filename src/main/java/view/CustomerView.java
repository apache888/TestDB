package view;

import controller.Controller;
import controller.ProjectController;
import exception.NoSuchIdException;
import exception.NotUniqueIdException;
import exception.NotUniqueNameException;
import model.Customer;
import model.Project;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Create by Roman Hayda on 29.03.2017.
 */
public class CustomerView implements View {
    private Controller<Customer> controller;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void fireEventCreate() throws NotUniqueNameException, NotUniqueIdException {
        ConsoleHelper.writeToConsole("Creating Customer object ...");
        controller.onCreate(createCustomer());
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
        ConsoleHelper.writeToConsole("Creating Customer object for update...");
        controller.onUpdate(createCustomer());

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
    private void writeById(Customer customer) {
        if (customer == null || (customer.getId() == 0 && customer.getName() == null)){
            ConsoleHelper.writeToConsole("\nThere is no such ID\n");
        }else {
            ConsoleHelper.writeToConsole("\n" + customer.toString() + "\n");
        }
    }

    //write to console information about received list of objects
    private void writeAll(List<Customer> list) {
        if (list.isEmpty()) {
            ConsoleHelper.writeToConsole("\nThere are no records to view.\n");
        } else {
            ConsoleHelper.writeToConsole("\nAll records of table 'customers'\n");
            for (Customer customer : list) {
                ConsoleHelper.writeToConsole(customer.toString());
            }
            ConsoleHelper.writeToConsole("\n");
        }
    }

    // create object by console dialog
    private Customer createCustomer() {
        Customer customer;
        int id;
        String customerName;
        Set<Project> projects = new HashSet<>();

        while (true) {
            try {
                ConsoleHelper.writeToConsole("Input id (if you want auto increment for 'Creating', input zero):");
                id = Integer.parseInt(ConsoleHelper.readString());
                break;
            } catch (IOException | NumberFormatException e) {
                ConsoleHelper.writeToConsole("Wrong integer. Try again");
            }
        }
        while (true) {
            try {
                ConsoleHelper.writeToConsole("Input customer name:");
                customerName = ConsoleHelper.readString();
                break;
            } catch (IOException e) {
                ConsoleHelper.writeToConsole("Failed input. Try again");
            }
        }
        while (true) {
            try {
                ConsoleHelper.writeToConsole("Input project's IDs of company: (for finish input 'exit')");
                String str;
                ProjectController projectController = new ProjectController();
                Set<Integer> idSet = new HashSet<>();
                while (!(str = ConsoleHelper.readString()).equalsIgnoreCase("exit")) {
                    try {
                        idSet.add(Integer.parseInt(str));
                    } catch (NumberFormatException e) {
                        ConsoleHelper.writeToConsole("Wrong integer. Try again");
                    }
                }
                try {
                    for (Integer idProject : idSet) {
                        projects.add(projectController.onGetById(idProject));
                    }
                } catch (NoSuchIdException e) {
                    ConsoleHelper.writeToConsole(e.getMessage());
                }
                break;
            } catch (IOException e) {
                ConsoleHelper.writeToConsole(e.getMessage());
            }
        }
        customer = new Customer(id, customerName);
        customer.setCustomerProjects(projects);

        return customer;
    }
}
