package com.fyp.fyp.dao;

import com.fyp.fyp.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface QuestionOptionsDao extends JpaRepository<QuestionOptions, QuestionOptionsKey> {
    Set<QuestionOptions> findAllByIdQuestionID(Long questionID);
}
