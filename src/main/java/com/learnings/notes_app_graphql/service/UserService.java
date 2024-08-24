package com.learnings.notes_app_graphql.service;

import com.learnings.notes_app_graphql.entity.User;
import com.learnings.notes_app_graphql.input.CreateUserInput;
import com.learnings.notes_app_graphql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
        return userRepository.findByUsername(username);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
