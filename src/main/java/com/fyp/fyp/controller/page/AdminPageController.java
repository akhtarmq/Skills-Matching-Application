package com.fyp.fyp.controller.page;

import com.fyp.fyp.entity.User;
import com.fyp.fyp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminPageController {

    @Autowired
    UserService userService;
//Check to see if the admin can allocate a role
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "admin/admin";
    }
//Edits a Users details
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/editUser")
    public String editUser(@ModelAttribute("username") String username,
                           Model model) {
        //TODO if not found throw error
        model.addAttribute("user", userService.findById(username).get());
        return "admin/adminSelectUser";
    }
//Creates a New User
    //Shows new User page
    @GetMapping("/adminNewUser")
    public String adminNewUser(Model model) {
        model.addAttribute("userTypes",userService.getAllUserTypes());
        return "admin/adminNewUser";
    }

    //Saves new user to db
    @PostMapping("/newUser")
    public String newUser(@ModelAttribute("user") User user,
                          Model model) {
        //TODO validate duplicates
        userService.addUser(user);
        return admin(model);
    }

    //saves the user to the main page
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user,
                           Model model) {
        //TODO if not found throw error - and do not create new user
        userService.updateUser(user);
        return admin(model);
    }
}
