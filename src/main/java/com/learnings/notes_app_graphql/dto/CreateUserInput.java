package com.learnings.notes_app_graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserInput {
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String email;
}
