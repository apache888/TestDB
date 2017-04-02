package dao;

import exception.NoSuchIdException;
import exception.NotUniqueIdException;
import exception.NotUniqueNameException;
import model.Skill;

import java.util.List;

/**
 * Create by Roman Hayda on 24.03.2017.
 */
//DAO layer for object Skill
public interface SkillDao{

    //create object in database
    void create(Skill skill) throws NotUniqueIdException, NotUniqueNameException;

    //receive object by id from database
    Skill getById(int id) throws NoSuchIdException;

    //receive list of all objects from database
    List<Skill> getAll();

    //update object in database
    void update(Skill skill) throws NotUniqueNameException, NotUniqueIdException;

    //delete object from database
    void delete(int id);
}
