package com.learnings.notes_app_graphql.auth;

import com.learnings.notes_app_graphql.entity.User;
import com.learnings.notes_app_graphql.exception_handling.exception.UserNotFoundException;
import com.learnings.notes_app_graphql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new CustomUserDetails(user);
        } catch (UsernameNotFoundException e) {
            throw new UserNotFoundException(username);
        }
    }
}