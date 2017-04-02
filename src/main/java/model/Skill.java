package model;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
public class Skill extends BaseObject {

    public Skill(int id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", specialty='" + name + '\'' +
                '}';
    }

    //todo
    //override hashCode and equals, correct createXxxx in xxxView
}
