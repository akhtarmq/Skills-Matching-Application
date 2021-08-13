package com.fyp.fyp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//initiates all of the fields that will display a question
public class Question {
    /**
     * primary key for table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long questionID;

    /**
     * Type of question.
     * Only allowed types are specified in enum
     */
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    /**
     * The text of a question
     */
    private String questionText;

    @OneToMany(mappedBy = "question")
    private Set<QuestionOptions> options;

}
