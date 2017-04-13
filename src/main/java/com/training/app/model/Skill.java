package com.training.app.model;


import javax.persistence.*;

/**
 * Create on 24.03.2017.
 * @author Roman Hayda
 *
 * Class describes entity Skill
 */

@Entity
@Table(name = "skills", catalog = "it_test_db")
public class Skill extends BaseObject{

    public Skill() {

    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", specialty='" + getName() + '\'' +
                '}';
    }
}
