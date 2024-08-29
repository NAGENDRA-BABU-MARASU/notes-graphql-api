package com.learnings.notes_app_graphql.exception_handling.exception;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(){
        super("Wrong password");
    }
}
