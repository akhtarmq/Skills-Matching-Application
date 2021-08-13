package com.fyp.fyp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Represents Questions for a jobspec
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class JobSpecQuestions {
    //initiates all of the field for the job spec questions to be created.

    @EmbeddedId
    @EqualsAndHashCode.Include
    private JobSpecQuestionsKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("jobSpecID")
    //@JoinColumn(name = "job_spec_id")
    private JobSpec jobSpec;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("questionID")
    //@JoinColumn(name = "question_id")
    private Question question;

    private int expectedSkillsWeighting;
    private int matchResponseID;

//    public JobSpecQuestions(JobSpecQuestionsKey id, int expectedSkillsWeighting, int matchResponseID) {
//        this.id = id;
//        this.expectedSkillsWeighting = expectedSkillsWeighting;
//        this.matchResponseID = matchResponseID;
//    }
}

