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
}
