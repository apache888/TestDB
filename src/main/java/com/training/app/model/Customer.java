package com.training.app.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Create on 24.03.2017.
 * @author Roman Hayda
 *
 * Class describes entity Customer
 */

@Entity
@Table(name = "customers", catalog = "it_test_db")
public class Customer extends BaseObject {

    @ManyToMany
    @JoinTable(name="customers_projects",
            joinColumns= @JoinColumn(name="customer_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="project_id", referencedColumnName="id")
    )
    private Set<Project> customerProjects;

    public Customer() {

    }

    public Customer(int id, String name) {
        super(id, name);
    }

    public Set<Project> getCustomerProjects() {
        return customerProjects;
    }

    public void setCustomerProjects(Set<Project> customerProjects) {
        this.customerProjects = customerProjects;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", projects:\n");
        for (Project project : customerProjects) {
            builder.append("Project{id=");
            builder.append(project.getId());
            builder.append(", name='");
            builder.append(project.getName());
            builder.append("\'},\n");
        }
        return builder.substring(0, builder.length() - 2) + "}\n";
    }
}
