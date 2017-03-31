package model;

import java.util.Set;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
public class Company extends BaseObject {
    private Set<Project> companyProjects;

    public Company(int id, String name) {
        super(id, name);
    }

    public Set<Project> getCompanyProjects() {
        return companyProjects;
    }

    public void setCompanyProjects(Set<Project> companyProjects) {
        this.companyProjects = companyProjects;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", projects:\n");
        for (Project project : companyProjects) {
            builder.append("Project{id=");
            builder.append(project.getId());
            builder.append(", name='");
            builder.append(project.getName());
            builder.append("\'},\n");
        }
        return builder.substring(0, builder.length() - 2) + "}\n";
    }
}
