package model;

import java.util.Set;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
public class Customer extends BaseObject {
    private Set<Project> customerProjects;

    public Customer(int id, String name) {
        super(id, name);
    }

    public Set<Project> getCustomerProjects() {
        return customerProjects;
    }
}
