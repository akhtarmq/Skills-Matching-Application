package com.fyp.fyp.dao;

import com.fyp.fyp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Manages relation between Question Entity objects and Database.
 */
@Repository
public interface QuestionDao extends JpaRepository<Question, Long> {

    List<Question> findByQuestionTextContainingIgnoreCase(String questionText);

    @Query(value = """
            SELECT q.* 
              FROM question q
             WHERE NOT EXISTS
                   (SELECT 'x' 
                      FROM user u,
                           candidate_response qr
                     WHERE q.questionid = qr.question_questionid
                       AND u.username = qr.username
                       AND u.username = ?1)
            """,
            nativeQuery = true)
    List<Question> getUnansweredQuestions(String username);

    @Query(value = """
            SELECT q.* 
              FROM question q
             WHERE EXISTS
                   (SELECT 'x' 
                      FROM user u,
                           candidate_response qr
                     WHERE q.questionid = qr.question_questionid
                       AND u.username = qr.username
                       AND u.username = ?1)
            """,
            nativeQuery = true)
    List<Question> getAnsweredQuestions(String username);
}

