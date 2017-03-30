package view;

import controller.Controller;

/**
 * Create by Roman Hayda on 28.03.2017.
 */
public interface View {
    void setController(Controller controller);

    void fireEventCreate();
    void fireEventGetById();
    void fireEventGetAll();
    void fireEventUpdate();
    void fireEventDelete();
}
