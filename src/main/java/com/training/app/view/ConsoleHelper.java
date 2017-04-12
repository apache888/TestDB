package com.training.app.view;

import com.training.app.controller.*;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Create by Roman Hayda on 23.03.2017.
 */
//Class for console working
public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private View view;
    private Controller controller;

    //main method to start application
    public void startApp() {
        showEntitiesMenu();
    }

    //method show all entities to select
    private void showEntitiesMenu() {
        while (true) {
            try {
                writeToConsole("\nSelect entity:\n\n" +
                        "0 - Developer\n" +
                        "1 - Skill\n" +
                        "2 - Project\n" +
                        "3 - IT Company\n" +
                        "4 - Customer\n" +
                        "5 - Exit\n");
                switch (Integer.parseInt(readString())) {
                    case 0:
                        view = new DeveloperView();
                        controller = new DeveloperController();
                        view.setController(controller);
                        showCommandsMenu();
                        break;
                    case 1:
                        view = new SkillView();
                        controller = new SkillController();
                        view.setController(controller);
                        showCommandsMenu();
                        break;
                    case 2:
                        view = new ProjectView();
                        controller = new ProjectController();
                        view.setController(controller);
                        showCommandsMenu();
                        break;
                    case 3:
                        view = new CompanyView();
                        controller = new CompanyController();
                        view.setController(controller);
                        showCommandsMenu();
                        break;
                    case 4:
                        view = new CustomerView();
                        controller = new CustomerController();
                        view.setController(controller);
                        showCommandsMenu();
                        break;
                    case 5:
                        return;
                    default:
                        throw new IllegalArgumentException();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                writeToConsole("Wrong select. Try again.");
            }
        }
    }

    //method show all CRUD commands to manipulate entities
    private void showCommandsMenu() {
        while (true) {
            try {
                writeToConsole("\nSelect command:\n\n" +
                        "0 - Create\n" +
                        "1 - Select all\n" +
                        "2 - Select by id\n" +
                        "3 - Update by id\n" +
                        "4 - Delete by id\n" +
                        "5 - Back\n");
                switch (Integer.parseInt(readString())) {
                    case 0:
                        view.fireEventCreate();
                        ConsoleHelper.writeToConsole("Command executed successfully.");
                        break;
                    case 1:
                        view.fireEventGetAll();
                        break;
                    case 2:
                        view.fireEventGetById();
                        break;
                    case 3:
                        view.fireEventUpdate();
                        ConsoleHelper.writeToConsole("Command executed successfully.");
                        break;
                    case 4:
                        view.fireEventDelete();
                        ConsoleHelper.writeToConsole("Command executed successfully.");
                        break;
                    case 5:
                        return;
                    default:
                        throw new IllegalArgumentException();
                }
            } catch (IOException | NotUniqueNameException | NotUniqueIdException e) {
                writeToConsole(e.getMessage());
            } catch (IllegalArgumentException e) {
                writeToConsole(e.getMessage());
                writeToConsole("Wrong command. Try again.");
            }
        }
    }

    //read line from console to String
    public static String readString() throws IOException {
        return reader.readLine();
    }

    //write to console received message
    public static void writeToConsole(String message) {
        System.out.println(message);
    }
}
