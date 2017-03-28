package model.entities;

import java.util.Set;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
public class Developer extends BaseObject {
    private int experience = 1;
    private int salary = 500;
    private Set<Skill> skills;

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
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", experience=" + experience +
                ", salary=" + salary +
                ", skills=" + skills +
                '}';
    }
}
