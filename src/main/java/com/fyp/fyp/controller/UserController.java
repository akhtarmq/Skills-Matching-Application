package com.fyp.fyp.controller;

import com.fyp.fyp.entity.User;
import com.fyp.fyp.entity.UserType;
import com.fyp.fyp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    //returns the user typed in.
    @GetMapping("/getUser")
    public User getUser() {
        User user = new User();
        user.setUsername("abc");
        return user;
    }

    //Only allows the user to select from the usertypes
//    @GetMapping (value="/getUserTypes", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<UserType> userType()
//    {
//        return userService.getAllUserTypes();
//    }
}