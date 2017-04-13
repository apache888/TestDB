package com.training.app.model;

import javax.persistence.*;

/**
 * Create on 27.03.2017.
 * @author Roman Hayda
 *
 * Class describes base entity for exteded entities
 * contains fields @id, @name
 */

@MappedSuperclass
public abstract class BaseObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false)
    String name;

    public BaseObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public BaseObject() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
