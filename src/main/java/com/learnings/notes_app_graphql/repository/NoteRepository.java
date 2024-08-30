package com.learnings.notes_app_graphql.repository;

import com.learnings.notes_app_graphql.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NoteRepository extends JpaRepository<Note, UUID> {

}
