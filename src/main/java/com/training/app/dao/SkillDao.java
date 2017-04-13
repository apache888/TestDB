package com.training.app.dao;

import com.training.app.exception.NoSuchIdException;
import com.training.app.exception.NotUniqueIdException;
import com.training.app.exception.NotUniqueNameException;
import com.training.app.model.Skill;

import java.util.List;

/**
 * Create on 24.03.2017.
 * @author Roman Hayda
 *
 * interface describes DAO layer for entity Skill
 * contains CRUD methods
 */
public interface SkillDao{

    /**
     * create Skill object in database
     * @param skill - given Skill object
     * @throws NotUniqueIdException
     * @throws NotUniqueNameException
     */
    void create(Skill skill) throws NotUniqueIdException, NotUniqueNameException;

    /**
     * receive Skill object by ID from database
     * @param id - object ID in database
     * @return Skill object
     * @throws NoSuchIdException
     */
    Skill getById(int id) throws NoSuchIdException;

    /**
     * receive list of all Skill objects from database
     * @return List<Skill>
     */
    List<Skill> getAll();

    /**
     * update Skill object in database
     * @param skill - Skill object
     * @throws NotUniqueNameException
     * @throws NotUniqueIdException
     */
    void update(Skill skill) throws NotUniqueNameException, NotUniqueIdException;

    /**
     * delete Skill object from database by ID
     * @param id - object ID in database
     */
    void delete(int id);
}
