package com.fyp.fyp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionOptionsKey implements Serializable {
    /**
     * ID of the question that a user has responded to
     */
    private Long questionID;

    private Integer sortSequence;
}
