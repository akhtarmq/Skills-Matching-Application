package com.fyp.fyp.services;

import com.fyp.fyp.dao.UserDao;
import com.fyp.fyp.entity.User;
import com.fyp.fyp.entity.UserType;
import com.fyp.fyp.model.CandidateRanking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    //initialises the details for users.
    List<User> listUsers();
    Optional<User> findById(String username);
    User updateUser(User user);
    User addUser(User user);
    List<UserType> getAllUserTypes();
    List<CandidateRanking> listMatchedCandidates(Long jobSpecId);

//    public boolean checkUsernameExists(String username);
//    public User getUser(long userId) throws UserNotFoundException;
//    public User addUser(User user);
//
    //Checks if user is a valid user
    boolean validateUser(String username, String password);
}
