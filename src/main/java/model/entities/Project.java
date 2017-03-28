package model.entities;

import java.util.Set;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
public class Project extends BaseObject{
    private int cost = 1000;
    private Set<Developer> projectDevelopers;

    public Project(int id, String name) {
        super(id, name);
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Set<Developer> getProjectDevelopers() {
        return projectDevelopers;
    }
}
