package view;

import controller.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Create by Roman Hayda on 23.03.2017.
 */
public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private View view;
    private Controller controller;

    public void startApp() {
        showEntitiesMenu();
    }

    private void showEntitiesMenu() {
        while (true) {
            try {
                writeToConsole("Select enttity:\n\n" +
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

    private void showCommandsMenu() {
        while (true) {
            try {
                writeToConsole("Select command:\n\n" +
                        "0 - Create\n" +
                        "1 - Select all\n" +
                        "2 - Select by id\n" +
                        "3 - Update by id\n" +
                        "4 - Delete by id\n" +
                        "5 - Back\n");
                switch (Integer.parseInt(readString())) {
                    case 0:
                        view.fireEventCreate();
                        break;
                    case 1:
                        view.fireEventGetAll();
                        break;
                    case 2:
                        view.fireEventGetById();
                        break;
                    case 3:
                        view.fireEventUpdate();
                        break;
                    case 4:
                        view.fireEventDelete();
                        break;
                    case 5:
                        return;
                    default:
                        throw new IllegalArgumentException();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                writeToConsole("Wrong command. Try again.");
            }
        }
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static void writeToConsole(String message) {
        System.out.println(message);
    }
}
