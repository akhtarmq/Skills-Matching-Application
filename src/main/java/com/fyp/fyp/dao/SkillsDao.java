package com.fyp.fyp.dao;

import com.fyp.fyp.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Manages relationship between skills entity objects and database
 */
@Repository
public interface SkillsDao extends JpaRepository<Skill, String> {

}

