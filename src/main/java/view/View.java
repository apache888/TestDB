package view;

import controller.Controller;
import exception.NotUniqueIdException;
import exception.NotUniqueNameException;

/**
 * Create by Roman Hayda on 28.03.2017.
 */
public interface View {
    void setController(Controller controller);

    void fireEventCreate() throws NotUniqueNameException, NotUniqueIdException;
    void fireEventGetById();
    void fireEventGetAll();
    void fireEventUpdate() throws NotUniqueNameException, NotUniqueIdException;
    void fireEventDelete();
}
