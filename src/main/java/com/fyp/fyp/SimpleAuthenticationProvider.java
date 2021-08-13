package com.fyp.fyp;

import java.util.ArrayList;

import com.fyp.fyp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class SimpleAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
    UserService userService;

	//Authenticates user
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
 
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        if (isUserValid(name, password)) {
 
            // use the credentials
            // and authenticate against the third-party system
            return new UsernamePasswordAuthenticationToken(
              name, password, new ArrayList<>());
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    //Checks to see if the user is valid
    public boolean isUserValid(String username, String password) {
    	return userService.validateUser(username, password);
    }
}
