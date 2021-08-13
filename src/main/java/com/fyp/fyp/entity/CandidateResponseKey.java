package com.fyp.fyp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Represents a composite primary key for the Question table
 */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateResponseKey implements Serializable {
    /**
     * Username of user who has answered a question
     */
    private String username;

    /**
     * ID of the question that a user has responded to
     */
    private Long questionID;
}
