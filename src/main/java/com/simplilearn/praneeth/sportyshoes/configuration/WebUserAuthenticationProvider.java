package com.simplilearn.praneeth.sportyshoes.configuration;

import com.simplilearn.praneeth.sportyshoes.model.User;
import com.simplilearn.praneeth.sportyshoes.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class WebUserAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public WebUserAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication.isAuthenticated()) {
            return authentication;
        }
        try {
            String username = (String) authentication.getPrincipal();
            String password = (String) authentication.getCredentials();

            Optional<User> user = userService.findByUsername(username);
            if (user.isPresent()) {
                if (!passwordEncoder.matches(password, user.get().getPassword())) {
                    return null;
                }
                authentication= new UserAuthentication(user.get());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
