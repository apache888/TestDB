package controller;

import model.Model;
import view.View;

/**
 * Create by Roman Hayda on 28.03.2017.
 */
public interface Controller {
    void setView(View view);
    void setModel(Model model);

    void onCreate();
    void onGetById(int id);
    void onGetAll();
    void onUpdate(int id);
    void onDelete(int id);
}
