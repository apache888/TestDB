package view;

import controller.Controller;
import model.entities.BaseObject;

import java.util.List;

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

    void writeById(BaseObject obj);
    void writeAll(List<? extends BaseObject> list);


}
