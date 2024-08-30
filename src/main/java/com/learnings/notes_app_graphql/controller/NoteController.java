package com.learnings.notes_app_graphql.controller;

import com.learnings.notes_app_graphql.dto.CreateNoteInput;
import com.learnings.notes_app_graphql.dto.NoteResponse;
import com.learnings.notes_app_graphql.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PreAuthorize("isAuthenticated()")
    @MutationMapping
    public NoteResponse createNote(@Argument CreateNoteInput createNoteInput) {
        return noteService.createNote(createNoteInput);
    }
}
