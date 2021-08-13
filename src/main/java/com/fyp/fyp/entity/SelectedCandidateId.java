package com.fyp.fyp.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Represents the ID for a selected candidate
 */
@Embeddable
@Data
public class SelectedCandidateId implements Serializable {
    /**
     * Username of a selected candidate
     */
    private String username;

    /**
     *
     */
    private Long jobSpecID;

}
