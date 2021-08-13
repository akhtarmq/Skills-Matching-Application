package com.fyp.fyp.dao;


import com.fyp.fyp.entity.JobSpec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Manages relation between Jobspec Entity objects and Database.
 */
@Repository
public interface JobSpecDao extends JpaRepository<JobSpec, Long> {

}
