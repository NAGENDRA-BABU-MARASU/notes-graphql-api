package com.learnings.notes_app_graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginInput {
    private String username;
    private String password;
}
