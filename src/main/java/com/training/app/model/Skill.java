package com.training.app.model;


import javax.persistence.*;

/**
 * Create by Roman Hayda on 24.03.2017.
 */

@Entity
@Table(name = "skills", catalog = "it_test_db")
public class Skill {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String specialty;

    /*public Skill(int id, String specialty) {
        this.id = id;
        this.specialty = specialty;
    }*/
    public Skill(String specialty) {
        this.specialty = specialty;
    }

    public Skill() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", specialty='" + specialty + '\'' +
                '}';
    }

    //todo
    //override hashCode and equals, correct createXxxx in xxxView
}
