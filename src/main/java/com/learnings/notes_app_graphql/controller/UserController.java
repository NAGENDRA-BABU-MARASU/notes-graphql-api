package com.learnings.notes_app_graphql.controller;

import com.learnings.notes_app_graphql.dto.AuthResponse;
import com.learnings.notes_app_graphql.dto.LoginInput;
import com.learnings.notes_app_graphql.entity.User;
import com.learnings.notes_app_graphql.dto.CreateUserInput;
import com.learnings.notes_app_graphql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @QueryMapping
    public User getUser(@Argument("username") String username) {
        return userService.getUser(username);
    }

    @PreAuthorize("isAnonymous()")
    @MutationMapping
    public User createUser(@Argument CreateUserInput createUserInput){
        return userService.createUser(createUserInput);
    }

    @PreAuthorize("isAnonymous()")
    @MutationMapping
    public AuthResponse login(@Argument LoginInput loginInput) {
        return userService.login(loginInput.getUsername(), loginInput.getPassword());
    }
}
