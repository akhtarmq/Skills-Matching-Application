package com.fyp.fyp.dao;

import com.fyp.fyp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Manages relationship between User entity objects and Database.
 */
@Repository
public interface UserDao extends JpaRepository<User, String> {

    /**
     * Finds User in User table in database with the username and password specified
     * @param username Username of User to find in database
     * @param password Password of that User
     * @return list of users matching username and password
     */
    List<User> findByUsernameAndPassword(String username, String password);
}
