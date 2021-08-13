package com.fyp.fyp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    /**
     * Primary key for table
     */
    @Id
    //initiates all of the fields that are used when displaying a user
    @EqualsAndHashCode.Include
    private String username;

    /**
     * The email of a user
     * (foreign key)
     */
    private String email;

    /**
     * The password of a user
     * (foreign key)
     */
    private String password;

    /**
     * Type of user
     * Only allowed types are specified in enum
     */
    @Enumerated(EnumType.STRING)
    private UserType type;
}
