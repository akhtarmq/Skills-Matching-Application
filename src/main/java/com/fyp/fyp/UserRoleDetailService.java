package com.fyp.fyp;

import com.fyp.fyp.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
@Transactional
public class UserRoleDetailService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    public UserRoleDetailService() {
        super();
    }

    @Override
    //username handling
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final com.fyp.fyp.entity.User user =
                userDao.findById(username)
                        .orElseThrow(() -> new UsernameNotFoundException("username not found:" + username));

        try {
            return new User(user.getUsername(),
                            user.getPassword(),
                     true, true, true, true,
                            getAuthorities(user.getType().name()));
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(final String userType) {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userType));
        return authorities;
    }
}
