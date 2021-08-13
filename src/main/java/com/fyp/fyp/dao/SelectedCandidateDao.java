package com.fyp.fyp.dao;

import com.fyp.fyp.entity.SelectedCandidate;
import com.fyp.fyp.entity.SelectedCandidateId;
import com.fyp.fyp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Manages relationship between the selected candidate entity objects and database
 */
@Repository
public interface SelectedCandidateDao extends JpaRepository<SelectedCandidate, SelectedCandidateId> {
    //List<SelectedCandidate> findByJobSpecID(int jobSpecID);
}
