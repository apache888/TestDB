package com.training.app.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Create on 24.03.2017.
 * @author Roman Hayda
 *
 * Class describes entity Developer
 */

@Entity
@Table(name = "developers", catalog = "it_test_db")
public class Developer extends BaseObject {

    @Column(name = "experience", nullable = false)
    private int experience = 1;
    @Column
    private int salary = 500;
    @ManyToMany
    @JoinTable(name="developers_skills",
            joinColumns= @JoinColumn(name="developer_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="skill_id", referencedColumnName="id")
    )
    private Set<Skill> skills;

    public Developer() {

    }

    public Developer(int id, String name) {
        super(id, name);
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", experience=" + experience +
                ", salary=" + salary +
                ", skills:\n");
        for (Skill skill : skills) {
            builder.append(skill.toString());
            builder.append(",\n");
        }
        return builder.substring(0, builder.length() - 2) + "}\n";
    }
}
