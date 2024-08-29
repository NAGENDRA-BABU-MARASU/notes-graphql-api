package com.learnings.notes_app_graphql.service;

import com.learnings.notes_app_graphql.dto.AuthResponse;
import com.learnings.notes_app_graphql.dto.CreateUserInput;
import com.learnings.notes_app_graphql.entity.User;
import com.learnings.notes_app_graphql.exception_handling.exception.WrongPasswordException;
import com.learnings.notes_app_graphql.repository.UserRepository;
import com.learnings.notes_app_graphql.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public User createUser(CreateUserInput createUserInput) {
        User user = new User();
        user.setUsername(createUserInput.getUsername());
        user.setEmail(createUserInput.getEmail());
        user.setFirst_name(createUserInput.getFirst_name());
        user.setLast_name(createUserInput.getLast_name());
        String hashedPassword = encodePassword(createUserInput.getPassword());
        user.setPassword_hash(hashedPassword);
        user.setCreated_at(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username).get();
    }

    public AuthResponse login(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtil.generateToken(authentication);
            return new AuthResponse(token);
        } catch(BadCredentialsException ex) {
            throw new WrongPasswordException();
        }

    }


    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
