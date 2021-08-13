package com.fyp.fyp.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Skill {
    /**
     * Primary key for table
     */
    @Id
    //initiates the field that displays individuals skills.
    private String skillName;
}
