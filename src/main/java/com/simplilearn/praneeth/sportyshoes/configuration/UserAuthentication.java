package com.simplilearn.praneeth.sportyshoes.configuration;

import com.simplilearn.praneeth.sportyshoes.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

public class UserAuthentication extends UsernamePasswordAuthenticationToken {

    private final User user;

    public UserAuthentication(User user) {
        super(user.getUsername(), user.getPassword(), authorities(user));
        this.user = user;
    }

    private static List<GrantedAuthority> authorities(User user) {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
    }

    public User getUser() {
        return user;
    }
}
