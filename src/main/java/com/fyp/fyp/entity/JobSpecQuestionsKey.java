package com.fyp.fyp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Represents Composite primary key for Job Spec Questions table.
 * Consists of Job Spec ID and Question ID
 * (Many to Many mapping)
 */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobSpecQuestionsKey implements Serializable {

    /**
     * Job Spec ID
     */
    //@Column(name = "job_spec_id")
    private Long jobSpecID;

    /**
     * Question ID linked to the Job Spec ID
     */
    //@Column(name = "question_id")
    private Long questionID;
}
