package com.fyp.fyp.dao;

import com.fyp.fyp.entity.JobSpecQuestions;
import com.fyp.fyp.entity.JobSpecQuestionsKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Manages relationship between a jobspec question entity objects and database
 */
@Repository
public interface JobSpecQuestionsDao extends JpaRepository<JobSpecQuestions, JobSpecQuestionsKey> {

}

