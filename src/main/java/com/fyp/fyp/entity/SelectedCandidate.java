package com.fyp.fyp.entity;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.IdClass;

/**
 * Represents a selected candidate
 */
@Entity
@Data
public class SelectedCandidate {
// initiates all of the fields that are taken in to count when selecting a candidate
    @EmbeddedId
    /**
     * Primary key for the table
     */
    private SelectedCandidateId selectedCandidateId;

    /**
     * If a user has been approved for a job or not
     */
    private Boolean approved;
}
