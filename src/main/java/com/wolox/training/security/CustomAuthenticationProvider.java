package com.wolox.training.security;

import com.wolox.training.exceptions.ValidationLoginException;
import com.wolox.training.services.UserService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (userService.validateLogin(userName, password)) {
            return new UsernamePasswordAuthenticationToken(userName, password, new ArrayList<>());
        } else {
            throw new ValidationLoginException();
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
