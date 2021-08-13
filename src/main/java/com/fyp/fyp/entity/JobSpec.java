package com.fyp.fyp.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.Incubating;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

/**
 * Represents a jobSpec
 * maps a jobspec table in the database
 */
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class JobSpec {

    /**
     * Primary key for table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long jobSpecID;

    /**
     * All questions included in this Job Spec.
     */
    @OneToMany(mappedBy = "jobSpec", fetch = FetchType.LAZY)
    private Set<JobSpecQuestions> questions;

    /**
     * The hiring manager who created this Job Spec
     */
    @ManyToOne(fetch = FetchType.LAZY)
    //cascade=CascadeType.ALL, fetch = FetchType.LAZY
    //insertable = false, updatable = false
    @JoinColumn(name="username", referencedColumnName = "username")
    /**
     * The user that's created the jobspec
     */
    private User createdBy;

    /**
     * job grade for the jobspec
     */
    private int jobGrade;

    /**
     * Name of the jobspec
     */
    private String jobSpecName;

    /**
     * Description of a job
     */
    private String jobDescription;

    /**
     * The closing date for a job
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate closingDate;

    /**
     * The date a jobspec is created
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate creationDate;

    /**
     * The type of contract a job is
     */
    private String contractType;

    /**
     * The contract hours a job has
     */
    private String contractHours;

    /**
     * The status of a jobs.
     */
    private String jobStatus;
}
