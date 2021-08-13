package com.fyp.fyp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class QuestionOptions {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private QuestionOptionsKey id;

    @ManyToOne()
    @MapsId("questionID")
    private Question question;

    private String optionText;

    private Integer weighting;
}
