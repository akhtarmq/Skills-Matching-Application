package com.fyp.fyp.controller;

import com.fyp.fyp.controller.page.AdminPageController;
import com.fyp.fyp.controller.page.CandidatePageController;
import com.fyp.fyp.controller.page.ManagerPageController;
import com.fyp.fyp.controller.page.HrPageController;
import com.fyp.fyp.entity.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

@Controller
public class LoginController {
//sets up the different mappings for the different user types.
    @Autowired
    AdminPageController adminController;

    @Autowired
    CandidatePageController candidateController;

    @Autowired
    ManagerPageController hmController;

    @Autowired
    HrPageController hrController;

//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
// Allows the different users to login to their different pages.

    @PostMapping("/loggedin")
    public RedirectView loggedin(Model model) {
        String landingPage = "login";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> roles = auth.getAuthorities();
        Optional<? extends GrantedAuthority> gAOptional = roles.stream().findFirst();
        if(gAOptional.isPresent()) {
            //TODO handle exception
            UserType userType = UserType.valueOf(gAOptional.get().getAuthority());
            switch(userType) {
                case ROLE_ADMIN: landingPage = "/admin"; break;
                case ROLE_MANAGER: landingPage = "/hm"; break;
                case ROLE_HR: landingPage = "/hr"; break;
                case ROLE_CANDIDATE: landingPage = "/candidate"; break;
            }
        }

        return new RedirectView(landingPage);
    }
}