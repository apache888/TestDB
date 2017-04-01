package dao;

import exception.NoSuchIdException;
import exception.NotUniqueIdException;
import exception.NotUniqueNameException;
import model.Skill;

import java.util.List;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
public interface SkillDao{

    void create(Skill skill) throws NotUniqueIdException, NotUniqueNameException;

    Skill getById(int id) throws NoSuchIdException;

    List<Skill> getAll();

    void update(Skill skill) throws NotUniqueNameException, NotUniqueIdException;

    void delete(int id);
}
