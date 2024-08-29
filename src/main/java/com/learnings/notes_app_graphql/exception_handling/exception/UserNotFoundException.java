package com.learnings.notes_app_graphql.exception_handling.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("User not found with username : " + username);
    }
}
