package com.training.app.model;

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

    public void setProjectDevelopers(Set<Developer> projectDevelopers) {
        this.projectDevelopers = projectDevelopers;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", developers:\n");
        for (Developer dev : projectDevelopers) {
            builder.append("Developer{id=");
            builder.append(dev.getId());
            builder.append(", name='");
            builder.append(dev.getName());
            builder.append("\'},\n");
        }
        return builder.substring(0, builder.length() - 2) + "}\n";
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37*result + id;
        result = 37*result + (name == null ? 0: name.hashCode());
        result = 37*result + cost;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Project project = (Project) obj;
        if (id != project.getId())
            return false;
        if (!name.equalsIgnoreCase(project.getName()))
            return false;
        if (cost != project.getCost())
            return false;

        return true;
    }
}
