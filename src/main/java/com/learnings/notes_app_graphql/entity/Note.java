package com.learnings.notes_app_graphql.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notes")

public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Lob
    private String content;
    private String title;
    private UUID user_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private UUID category_id;
}
