package dao;

import model.Skill;

import java.util.List;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
public interface SkillDao{

    void create(Skill skill);

    Skill getById(int id);

    List<Skill> getAll();

    void update(Skill skill);

    void delete(int id);
}
