package com.fyp.fyp.dao;

import com.fyp.fyp.entity.CandidateResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Manages relationship between a Candidates Response Entity object and the database
 */
@Repository
public interface CandidateResponseDao extends JpaRepository<CandidateResponse, Long> {
    List<CandidateResponse> findAllByIdUsername(String username);
}

