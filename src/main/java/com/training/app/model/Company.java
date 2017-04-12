package com.training.app.model;

import java.util.Map;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
public class Company extends BaseObject {
    private Map<Project, Integer> companyProjects;

    public Company(int id, String name) {
        super(id, name);
    }

    public Map<Project, Integer> getCompanyProjects() {
        return companyProjects;
    }

    public void setCompanyProjects(Map<Project, Integer> companyProjects) {
        this.companyProjects = companyProjects;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", projects:\n");
        for (Project project : companyProjects.keySet()) {
            builder.append("Project{id=");
            builder.append(project.getId());
            builder.append(", name='");
            builder.append(project.getName());
            builder.append("\'},\n");
        }
        return builder.substring(0, builder.length() - 2) + "}\n";
    }
}
