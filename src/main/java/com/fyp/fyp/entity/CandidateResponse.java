package com.fyp.fyp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//TODO - rename this to CandidateResponse
/**
 * Represents a question Response
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CandidateResponse {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private CandidateResponseKey id;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @MapsId("questionID")
    //@JoinColumn(name = "question_questionID")
    private Question question;

    private int responseNum;
}
