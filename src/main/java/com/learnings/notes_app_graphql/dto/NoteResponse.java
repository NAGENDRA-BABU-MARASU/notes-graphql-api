package com.learnings.notes_app_graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteResponse {
    private UUID id;
    private String content;
    private String title;
    private String username;
    private String category;
}
