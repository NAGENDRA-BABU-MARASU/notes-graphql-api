package com.learnings.notes_app_graphql.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnings.notes_app_graphql.auth.CustomUserDetails;
import com.learnings.notes_app_graphql.dto.CreateNoteInput;
import com.learnings.notes_app_graphql.dto.NoteResponse;
import com.learnings.notes_app_graphql.entity.Note;
import com.learnings.notes_app_graphql.repository.CategoryRepository;
import com.learnings.notes_app_graphql.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class NoteService {


    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    public NoteResponse createNote(CreateNoteInput createNoteInput) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Note note = new Note();
        note.setTitle(createNoteInput.getTitle());
        note.setContent((createNoteInput.getContent()));
        note.setCreated_at(LocalDateTime.now());
        note.setUpdated_at(LocalDateTime.now());
        note.setUser_id(customUserDetails.getUser().getId());
        note.setCategory_id(getCategoryId(customUserDetails.getUser().getId()));
        Note savedNote = noteRepository.save(note);
        return convertNoteToNoteResponse(savedNote);
    }

    private NoteResponse convertNoteToNoteResponse(Note note) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        NoteResponse noteResponse = new NoteResponse(
                note.getId(),
                note.getContent(),
                note.getTitle(),
                customUserDetails.getUsername(),
                "main"
        );

        return noteResponse;
    }

    private UUID getCategoryId(UUID userId){
        return categoryRepository.findAllByUserId(userId).get().get(0).getId();
    }
}
